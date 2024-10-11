/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.migration;

import id.ezclouds.common.facade.biz.election.VoterInvalidRegistrationService;
import id.ezclouds.common.facade.biz.election.VoterRegistrationService;
import id.ezclouds.common.facade.dal.biz.BizMigrationRecordDAO;
import id.ezclouds.common.facade.dal.member.CoreMemberDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.migration.BizMigrationRecord;
import id.ezclouds.common.model.biz.migration.MigrationScene;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.model.core.member.CoreMemberExtension;
import id.ezclouds.common.model.pagination.SortBy;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: InnerProcessMigrateMember.java, v 0.1 2024‐10‐05 5:02 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope("prototype")
public class InnerProcessMigrateMember {

    @Autowired
    private CoreMemberDAO coreMemberDAO;

    @Autowired
    private BizMigrationRecordDAO bizMigrationRecordDAO;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Autowired
    private VoterRegistrationService voterRegistrationService;

    @Autowired
    private VoterInvalidRegistrationService voterInvalidRegistrationService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public List<CoreMember> getMigrationMembers(String orgId, String date) {
        Date today = DateUtil.parseFormattedDate(date, DateUtil.FORMAT_DATE);
        String startDate = DateUtil.getFormattedDayStart(today);
        String endDate = DateUtil.getFormattedDayEnd(today);
        return coreMemberDAO.getMigrationMembers(orgId, startDate, endDate, SortBy.OLDEST, 1);
    }

    public boolean migrateMember(CoreMember coreMember) {
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        String memberRoles = coreMember.getRoles();

        boolean isOrgAdmin = StringUtil.isNotBlank(memberRoles) && Arrays.asList(memberRoles.split(",")).contains(AuthRole.ADMIN_ORG.getCode());
        if (isOrgAdmin) {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    CoreMember dbCoreMember = coreMemberDAO.getAndLock(coreMember.getMemberId());
                    dbCoreMember.setMigrationId("ADMIN_NOT_MIGRATED");
                    coreMemberDAO.store(dbCoreMember);
                }
            });
        }
        else {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    migrateMemberToVoter(coreMember);
                }
            });
        }

        return true;
    }

    private void migrateMemberToVoter(CoreMember coreMember) {
        ProcessStatus processStatus = ProcessStatus.INIT;
        EzErrorCode ezErrorCode = null;
        String recordId = initMigrationRecord(coreMember, processStatus);
        String voterId = null;
        String errorMessage = null;

        BizVoter bizVoter = composeBizVoter(coreMember);
        try {
            voterId = voterRegistrationService.registerVoter(bizVoter);
            processStatus = ProcessStatus.SUCCESS;
        } catch (EzErrorException ezException) {
            if (ezException.getEzErrorCode() == EzErrorCode.IDEMPOTENT_ERROR) {
                errorMessage = "Data Duplikat";
            }
            else if (ezException.getEzErrorCode() == EzErrorCode.BIZ_VALIDATION_FAILED) {
                errorMessage = "Tidak Lolos Validasi";
            }
            else {
                errorMessage = ezException.getEzErrorCode().getCode() + ExceptionUtil.getErrorContext(ezException);
            }
            processStatus = ProcessStatus.EXCEPTION;
            ezErrorCode = ezException.getEzErrorCode();

        } catch (Exception exception) {
            errorMessage = ExceptionUtil.getErrorContext(exception);
            processStatus = ProcessStatus.EXCEPTION;
            ezErrorCode = EzErrorCode.BIZ_VALIDATION_FAILED;
        }

        if (processStatus == ProcessStatus.EXCEPTION) {
            try {
                bizVoter.setVoterId(null);
                voterId = voterInvalidRegistrationService
                        .registerVoterInvalid(bizVoter, ezErrorCode.getCode(), errorMessage);
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage += " : voterInvalidRegister.Error";
                recordId = "STUCK_ERROR";
            }
        }

        BizMigrationRecord migrationRecord = bizMigrationRecordDAO.getAndLock(recordId);
        migrationRecord.setTargetId(voterId);
        migrationRecord.setStatus(processStatus.getCode());
        migrationRecord.setErrorMessage(errorMessage);
        migrationRecord.setTimestamp(DateUtil.getCurrentFormattedDateMillis());
        bizMigrationRecordDAO.store(migrationRecord);

        CoreMember dbCoreMember = coreMemberDAO.getAndLock(coreMember.getMemberId());
        dbCoreMember.setMigrationId(recordId);
        coreMemberDAO.store(dbCoreMember);
    }

    private String initMigrationRecord(CoreMember coreMember, ProcessStatus processStatus) {
        String orgId = coreMember.getOrgId();
        MigrationScene scene = MigrationScene.CORE_MEMBER_TO_BIZ_VOTER;
        String sourceId = coreMember.getMemberId();
        String recordId = HashUtil.createHash(orgId, scene.getCode(), sourceId);

        BizMigrationRecord migrationRecord = bizMigrationRecordDAO.getById(recordId);
        if (migrationRecord == null) {
            migrationRecord = new BizMigrationRecord();
            migrationRecord.setRecordId(recordId);
            migrationRecord.setOrgId(coreMember.getOrgId());
            migrationRecord.setScene(scene);
            migrationRecord.setSourceId(sourceId);
            migrationRecord.setPayload(bizObjectMapperService.toJson(coreMember));
            migrationRecord.setTimestamp(DateUtil.getCurrentFormattedDateMillis());
            migrationRecord.setStatus(processStatus.getCode());
            bizMigrationRecordDAO.store(migrationRecord);
        }

        return recordId;
    }

    private BizVoter composeBizVoter(CoreMember coreMember) {
        BizVoter bizVoter = new BizVoter();
        bizVoter.setOrgId(coreMember.getOrgId());
        bizVoter.setSubOrgId(coreMember.getSubOrgId());
        bizVoter.setSourceId(coreMember.getSourceId());
        bizVoter.setReferrerId(coreMember.getReferrerId());
        bizVoter.setFamilySize(0);
        bizVoter.setFamilySizeMale(0);
        bizVoter.setFamilySizeFemale(0);
        bizVoter.setName(StringUtil.toTitleCase(coreMember.getName()));
        bizVoter.setGender(coreMember.getGender().getCode());
        bizVoter.setDateOfBirth(coreMember.getDateOfBirth());
        bizVoter.setPhone(coreMember.getPhone());
        bizVoter.setEducation(coreMember.getEducation());
        bizVoter.setOccupation(coreMember.getOccupation());
        bizVoter.setReligion(coreMember.getReligion());
        bizVoter.setEthnic(coreMember.getEthnic());
        bizVoter.setEmail(coreMember.getEmail());
        bizVoter.setStatus(0);

        if (coreMember.getMemberExtension() != null) {
            CoreMemberExtension extension = coreMember.getMemberExtension();

            bizVoter.setIdCardNumber(extension.getIdCardNumber());
            bizVoter.setFamilyCardNumber(extension.getFamilyCardNumber());
            bizVoter.setProvinceId(extension.getProvinceId());
            bizVoter.setProvinceName(extension.getProvinceName());
            bizVoter.setRegencyId(extension.getRegencyId());
            bizVoter.setRegencyName(extension.getRegencyName());
            bizVoter.setDistrictId(extension.getDistrictId());
            bizVoter.setDistrictName(extension.getDistrictName());
            bizVoter.setVillageId(extension.getVillageId());
            bizVoter.setVillageName(extension.getVillageName());
            bizVoter.setNeighbourhood(extension.getNeighbourhood());
            bizVoter.setSubNeighbourhood(extension.getSubNeighbourhood());
            bizVoter.setPollStationId(extension.getPollStationId());
        }

        if (StringUtil.isNotBlank(coreMember.getRoles())) {
            bizVoter.setMemberId(coreMember.getMemberId());
        }

        return bizVoter;
    }
}