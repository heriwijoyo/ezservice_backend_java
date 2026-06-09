/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.facade.dal.biz.report.BizAccumulateAreaExtDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateAreaDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateClusterDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateMemberDAO;
import id.ezclouds.common.facade.dal.organization.SubOrganizationDAO;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.report.*;
import id.ezclouds.common.model.core.member.CoreGender;
import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateVoterRegister.java, v 0.1 2024‐10‐02 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope("prototype")
@Qualifier("bizReportAccumulateVoterRegister")
public class BizReportAccumulateVoterRegister implements ReportAccumulateProcessor {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Autowired
    private BizReportAccumulateAreaDAO bizReportAccumulateAreaDAO;

    @Autowired
    private BizAccumulateAreaExtDAO bizAccumulateAreaExtDAO;

    @Autowired
    private BizReportAccumulateMemberDAO bizReportAccumulateMemberDAO;

    @Autowired
    private CoreWorkingAreaService coreWorkingAreaService;

    @Autowired
    private SubOrganizationDAO subOrganizationDAO;

    @Autowired
    private BizReportAccumulateClusterDAO bizReportAccumulateClusterDAO;

    @Override
    public void process(String orgId, Object payload, ReportAccumulateProcessHandler handler) {
        BizVoter bizVoter = (BizVoter) payload;

        List<CoreAreaLevel> workingAreaLevels = coreWorkingAreaService.fetchAvailAreaLevel(orgId);

        try {

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {

                    accumulateOverall(bizVoter.getOrgId(), bizVoter);
                    for (CoreAreaLevel areaLevel : workingAreaLevels) {
                        accumulateVoterOnAreaLevel(areaLevel, bizVoter);
                    }

                    if (StringUtil.isNotBlank(bizVoter.getReferrerId())) {
                        processAccumulateMember(bizVoter);
                    }

                    if (StringUtil.isNotBlank(bizVoter.getSubOrgId())) {
                        processAccumulateCluster(bizVoter);
                    }
                }
            });
            handler.onFinished(ProcessStatus.SUCCESS, null);

        } catch (Exception e) {
            handler.onFinished(ProcessStatus.EXCEPTION, ExceptionUtil.getStackTrace(e));
        }
    }

    private void accumulateOverall(String orgId, BizVoter bizVoter) {
        BizReportOverall reportOverall = bizReportOverallDAO
                .getAndLock(orgId, BizReportOverallKey.VOTER_BASE_VOTER_COUNT);
        int increasedCount = reportOverall.getCount() + 1;

        reportOverall.setCount(increasedCount);
        bizReportOverallDAO.store(reportOverall);

        //accumulate statistic today
        String todayDate = DateUtil.getFormattedDate(new Date(), DateUtil.FORMAT_DATE);
        String registerDate = DateUtil.getFormattedDateFromDateTime(bizVoter.getCreatedTime());
        if (StringUtil.equals(todayDate, registerDate)) {
            BizReportOverall voterToday = bizReportOverallDAO
                    .getAndLock(orgId, BizReportOverallKey.VOTER_BASE_VOTER_COUNT_TODAY);
            int updateCount = voterToday.getCount() + 1;
            voterToday.setCount(updateCount);
            bizReportOverallDAO.store(voterToday);
        }
    }

    private void accumulateVoterOnAreaLevel(CoreAreaLevel areaLevel, BizVoter bizVoter) {
        String areaLevelId = null;
        switch (areaLevel) {
            case VILLAGE:
                areaLevelId = bizVoter.getVillageId();
                break;
            case DISTRICT:
                areaLevelId = bizVoter.getDistrictId();
                break;
            case REGENCY:
                areaLevelId = bizVoter.getRegencyId();
                break;
            case PROVINCE:
                areaLevelId = bizVoter.getProvinceId();
                break;
        }

        BizReportAccumulateArea accumulateArea = bizReportAccumulateAreaDAO
                .getAndLock(bizVoter.getOrgId(), areaLevel, areaLevelId);

        AssertUtil.notNull(accumulateArea, EzErrorCode.BIZ_PROCESS_ERROR);

        int voterCount = accumulateArea.getVoterCount();
        int voterMaleCount = accumulateArea.getVoterMaleCount();
        int voterFemaleCount = accumulateArea.getVoterFemaleCount();
        int voterExtraCount = accumulateArea.getVoterExtraCount();
        int voterExtraMaleCount = accumulateArea.getVoterExtraMaleCount();
        int voterExtraFemaleCount = accumulateArea.getVoterExtraFemaleCount();

        voterCount += 1;
        CoreGender coreGender = CoreGender.getByCode(bizVoter.getGender());
        if (coreGender == CoreGender.MALE) {
            voterMaleCount += 1;
        } else {
            voterFemaleCount += 1;
        }

        if (bizVoter.getFamilySize() > 0) {
            // voter extra = family size - main voter
            voterExtraCount = bizVoter.getFamilySize() - 1;

            if (bizVoter.getFamilySizeMale() > 0) {
                voterExtraMaleCount = (coreGender == CoreGender.MALE) ? bizVoter.getFamilySizeMale() - 1 : bizVoter.getFamilySizeMale();
            }
            if (bizVoter.getFamilySizeFemale() > 0) {
                voterExtraFemaleCount = (coreGender == CoreGender.FEMALE) ? bizVoter.getFamilySizeFemale() - 1 : bizVoter.getFamilySizeFemale();
            }
        }

        accumulateArea.setVoterCount(voterCount);
        accumulateArea.setVoterMaleCount(voterMaleCount);
        accumulateArea.setVoterFemaleCount(voterFemaleCount);
        accumulateArea.setVoterExtraCount(voterExtraCount);
        accumulateArea.setVoterExtraMaleCount(voterExtraMaleCount);
        accumulateArea.setVoterExtraFemaleCount(voterExtraFemaleCount);

        String currentTime = DateUtil.getCurrentFormattedDateMillis();
        accumulateArea.setModifiedTime(currentTime);

        bizReportAccumulateAreaDAO.store(accumulateArea);

        accumulateAreaExt(areaLevel, accumulateArea.getAccumulateAreaId(), bizVoter, currentTime);
    }

    private void accumulateAreaExt(CoreAreaLevel areaLevel, String accumulateAreaId, BizVoter bizVoter, String currentTime) {
        List<BizAccumulateKey> onlyVillageLevelKeys = new ArrayList<>();
        onlyVillageLevelKeys.add(BizAccumulateKey.POLL_STATION_ID);
        onlyVillageLevelKeys.add(BizAccumulateKey.NEIGHBOURHOOD);
        onlyVillageLevelKeys.add(BizAccumulateKey.SUB_NEIGHBOURHOOD);

        for (BizAccumulateKey accumulateKey : BizAccumulateKey.values()) {
            if (areaLevel != CoreAreaLevel.VILLAGE) {
                if (onlyVillageLevelKeys.contains(accumulateKey)) {
                    continue;
                }
            }

            String accumulateVariable = getAccumulateVariable(accumulateKey, bizVoter);

            if (StringUtil.isNotBlank(accumulateVariable)) {
                BizAccumulateAreaExt accumulateAreaExt = bizAccumulateAreaExtDAO.getAndLock(
                        accumulateAreaId,
                        bizVoter.getOrgId(),
                        accumulateKey.getCode(),
                        accumulateVariable
                );
                if (accumulateAreaExt == null) {
                    accumulateAreaExt = new BizAccumulateAreaExt();
                    accumulateAreaExt.setAccumulateAreaId(accumulateAreaId);
                    accumulateAreaExt.setOrgId(bizVoter.getOrgId());
                    accumulateAreaExt.setAreaLevel(areaLevel);
                    accumulateAreaExt.setAccumulateKey(accumulateKey);
                    accumulateAreaExt.setAccumulateVariable(accumulateVariable);
                    accumulateAreaExt.setAccumulateCount(0);
                }

                int increasedCount = accumulateAreaExt.getAccumulateCount() + 1;
                accumulateAreaExt.setAccumulateCount(increasedCount);
                accumulateAreaExt.setModifiedTime(currentTime);

                bizAccumulateAreaExtDAO.store(accumulateAreaExt);
            }
        }
    }

    private String getAccumulateVariable(BizAccumulateKey accumulateKey, BizVoter bizVoter) {
        switch (accumulateKey) {
            case RELIGION:
                return bizVoter.getReligion();
            case EDUCATION:
                return bizVoter.getEducation();
            case OCCUPATION:
                return bizVoter.getOccupation();
            case ETHNIC:
                return bizVoter.getEthnic();
            case POLL_STATION_ID:
                return bizVoter.getPollStationId();
            case NEIGHBOURHOOD:
                return bizVoter.getNeighbourhood();
            case SUB_NEIGHBOURHOOD:
                return bizVoter.getSubNeighbourhood();
        }
        return null;
    }

    private void processAccumulateMember(BizVoter bizVoter) {
        String accumulateId = HashUtil.createHash(
                bizVoter.getOrgId(),
                bizVoter.getReferrerId(),
                BizAccumulateMemberKey.VOTER_SUCCESS.getKey(),
                "DEFAULT"
        );

        BizReportAccumulateMember accumulateMember = bizReportAccumulateMemberDAO
                .getAndLock(accumulateId);
        if (accumulateMember == null) {
            accumulateMember = new BizReportAccumulateMember();
            accumulateMember.setAccumulateMemberId(accumulateId);
            accumulateMember.setOrgId(bizVoter.getOrgId());
            accumulateMember.setMemberId(bizVoter.getReferrerId());
            accumulateMember.setAccumulateKey(BizAccumulateMemberKey.VOTER_SUCCESS);
            accumulateMember.setAccumulateVariable("DEFAULT");
            accumulateMember.setAccumulateCount(0);
        }

        int increasedCount = accumulateMember.getAccumulateCount() + 1;
        accumulateMember.setAccumulateCount(increasedCount);
        accumulateMember.setModifiedTime(DateUtil.getCurrentFormattedDateMillis());

        bizReportAccumulateMemberDAO.store(accumulateMember);
    }

    private void processAccumulateCluster(BizVoter bizVoter) {
        SubOrganization subOrganization = subOrganizationDAO.getById(bizVoter.getSubOrgId());

        BizReportAccumulateCluster accumulateCluster = bizReportAccumulateClusterDAO
                .getAndLock(bizVoter.getOrgId(), bizVoter.getSubOrgId());
        if (accumulateCluster == null) {
            accumulateCluster = new BizReportAccumulateCluster();
            accumulateCluster.setOrgId(bizVoter.getOrgId());
            accumulateCluster.setClusterId(bizVoter.getSubOrgId());
            accumulateCluster.setClusterName(subOrganization.getName());
            accumulateCluster.setVoterCount(0);
            accumulateCluster.setVoterMaleCount(0);
            accumulateCluster.setVoterFemaleCount(0);
            accumulateCluster.setVoterExtraCount(0);
            accumulateCluster.setVoterExtraMaleCount(0);
            accumulateCluster.setVoterExtraFemaleCount(0);
        }

        int updateVoterCount = accumulateCluster.getVoterCount() + 1;
        accumulateCluster.setVoterCount(updateVoterCount);

        if (bizVoter.getFamilySize() > 1) {
            int extraCount = accumulateCluster.getVoterExtraCount();
            extraCount += (bizVoter.getFamilySize() - 1);
            accumulateCluster.setVoterExtraCount(extraCount);
        }

        CoreGender coreGender = CoreGender.getByCode(bizVoter.getGender());
        if (coreGender == CoreGender.MALE) {
            int updateMaleCount = accumulateCluster.getVoterMaleCount() + 1;
            accumulateCluster.setVoterMaleCount(updateMaleCount);

            if (bizVoter.getFamilySizeMale() > 1) {
                int extraMaleCount = accumulateCluster.getVoterExtraMaleCount();
                extraMaleCount += (bizVoter.getFamilySizeMale() - 1);
                accumulateCluster.setVoterExtraMaleCount(extraMaleCount);
            }
        }
        if (coreGender == CoreGender.FEMALE) {
            int updateFemaleCount = accumulateCluster.getVoterFemaleCount() + 1;
            accumulateCluster.setVoterFemaleCount(updateFemaleCount);

            if (bizVoter.getFamilySizeFemale() > 1) {
                int extraFemaleCount = accumulateCluster.getVoterExtraFemaleCount();
                extraFemaleCount += (bizVoter.getFamilySizeFemale() - 1);
                accumulateCluster.setVoterExtraFemaleCount(extraFemaleCount);
            }
        }

        accumulateCluster.setModifiedTime(DateUtil.getCurrentFormattedDateMillis());

        bizReportAccumulateClusterDAO.store(accumulateCluster);
    }
}