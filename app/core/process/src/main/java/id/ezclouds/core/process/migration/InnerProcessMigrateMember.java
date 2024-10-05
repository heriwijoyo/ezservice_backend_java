/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.migration;

import id.ezclouds.common.facade.biz.election.VoterRegistrationService;
import id.ezclouds.common.facade.dal.biz.BizMigrationRecordDAO;
import id.ezclouds.common.facade.dal.member.CoreMemberDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.migration.BizMigrationRecord;
import id.ezclouds.common.model.biz.migration.MigrationScene;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.model.pagination.SortBy;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: InnerProcessMigrateMember.java, v 0.1 2024‐10‐05 5:02 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
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
    private TransactionTemplate transactionTemplate;

    public List<CoreMember> getMigrationMembers(String orgId) {
        return coreMemberDAO.getMigrationMembers(orgId, SortBy.OLDEST, 100);
    }

    public void migrateMember(CoreMember coreMember) {
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
    }

    private void migrateMemberToVoter(CoreMember coreMember) {
        ProcessStatus processStatus = ProcessStatus.INIT;
        String recordId = initMigrationRecord(coreMember, processStatus);
        String voterId = null;
        String errorMessage = null;

        BizVoter bizVoter = composeBizVoter(coreMember);
        try {
            voterId = voterRegistrationService.registerVoter(bizVoter);
            processStatus = ProcessStatus.SUCCESS;
        } catch (Exception exception) {
            if (exception instanceof EzErrorException) {
                if (((EzErrorException)exception).getEzErrorCode() == EzErrorCode.IDEMPOTENT_ERROR) {
                    errorMessage = EzErrorCode.IDEMPOTENT_ERROR.getCode();
                }
            } else {
                errorMessage = ExceptionUtil.getErrorContext(exception);
            }
            processStatus = ProcessStatus.EXCEPTION;
        }

        BizMigrationRecord migrationRecord = bizMigrationRecordDAO.getAndLock(recordId);
        migrationRecord.setTargetId(voterId);
        migrationRecord.setStatus(processStatus.getCode());
        migrationRecord.setErrorMessage(errorMessage);
        migrationRecord.setTimestamp(DateUtil.getCurrentFormattedDateMillis());
        bizMigrationRecordDAO.store(migrationRecord);
    }

    private String initMigrationRecord(CoreMember coreMember, ProcessStatus processStatus) {
        String orgId = coreMember.getOrgId();
        MigrationScene scene = MigrationScene.CORE_MEMBER_TO_BIZ_VOTER;
        String sourceId = coreMember.getMemberId();
        String recordId = HashUtil.createHash(orgId, scene.getCode(), sourceId);

        BizMigrationRecord migrationRecord = new BizMigrationRecord();
        migrationRecord.setRecordId(recordId);
        migrationRecord.setOrgId(coreMember.getOrgId());
        migrationRecord.setScene(scene);
        migrationRecord.setSourceId(sourceId);
        migrationRecord.setPayload(bizObjectMapperService.toJson(coreMember));
        migrationRecord.setTimestamp(DateUtil.getCurrentFormattedDateMillis());
        migrationRecord.setStatus(processStatus.getCode());
        bizMigrationRecordDAO.store(migrationRecord);

        return recordId;
    }

    private BizVoter composeBizVoter(CoreMember coreMember) {
        return new BizVoter();
    }
}