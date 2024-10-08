/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateAreaDAO;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.report.BizReportAccumulateArea;
import id.ezclouds.common.model.core.member.CoreGender;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

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

    @Override
    public void process(String orgId, Object payload, ReportAccumulateProcessHandler handler) {
        BizVoter bizVoter = (BizVoter) payload;

        try {

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {

                    accumulateOverall(bizVoter.getOrgId());
                    accumulateVoterOnAreaLevel(CoreAreaLevel.VILLAGE, bizVoter);
                    accumulateVoterOnAreaLevel(CoreAreaLevel.DISTRICT, bizVoter);
                    accumulateVoterOnAreaLevel(CoreAreaLevel.REGENCY, bizVoter);
                    accumulateVoterOnAreaLevel(CoreAreaLevel.PROVINCE, bizVoter);

                }
            });
            handler.onFinished(ProcessStatus.SUCCESS);

        } catch (Exception e) {
            handler.onFinished(ProcessStatus.EXCEPTION);
        }
    }

    private void accumulateOverall(String orgId) {
        BizReportOverall reportOverall = bizReportOverallDAO
                .getAndLock(orgId, BizReportOverallKey.VOTER_BASE_VOTER_COUNT.getCode());
        int increasedCount = reportOverall.getCount() + 1;

        reportOverall.setCount(increasedCount);
        bizReportOverallDAO.store(reportOverall);
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

        if (accumulateArea != null) {
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
            accumulateArea.setModifiedTime(DateUtil.getCurrentFormattedDateMillis());

            bizReportAccumulateAreaDAO.store(accumulateArea);
        }
    }
}