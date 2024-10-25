/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.report;

import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateTimeSeriesDAO;
import id.ezclouds.common.facade.organization.SubOrganizationService;
import id.ezclouds.common.model.area.AreaInitConfig;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizReportAccumulateTimeSeries;
import id.ezclouds.common.model.biz.report.BizTimeSeriesScene;
import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.common.model.query.BizGroupQueryCount;
import id.ezclouds.common.model.util.CoreAreaUtil;
import id.ezclouds.common.model.util.TimeFrameUtil;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessGenerateReportAccumulateTimeSeries.java, v 0.1 2024‐10‐25 9:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessGenerateReportAccumulateTimeSeries extends BizAsyncProcessor {

    @Autowired
    private SubOrganizationService subOrganizationService;

    @Autowired
    private CoreWorkingAreaService coreWorkingAreaService;

    @Autowired
    private BizVoterDAO bizVoterDAO;

    @Autowired
    private BizReportAccumulateTimeSeriesDAO bizReportAccumulateTimeSeriesDAO;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.GENERATE_REPORT_ACCUMULATE_TIME_SERIES;
    }

    @Override
    protected int maxProcessTime() {
        return 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String param = (String) request;
        String orgId = param.split(",")[0];
        int nPrevTimeFrame = Integer.parseInt(param.split(",")[1]);

        logData.add("ORG_ID="+ orgId);
        logData.add(",NTF="+ nPrevTimeFrame);

        for (BizTimeSeriesScene timeSeriesScene : BizTimeSeriesScene.values()) {
            if (timeSeriesScene == BizTimeSeriesScene.UNKNOWN) {
                continue;
            }
            processGenerate(orgId, timeSeriesScene, nPrevTimeFrame, logData);
        }

        return true;
    }

    private void processGenerate(String orgId, BizTimeSeriesScene timeSeriesScene, int nPrevTimeFrame, List<String> logData) {
        List<SubOrganization> subOrganizations = new ArrayList<>();
        CoreAreaLevel areaLevel = CoreAreaLevel.REGENCY;

        if (timeSeriesScene == BizTimeSeriesScene.VOTER_PROGRESS_BY_CLUSTER_DAILY) {
            subOrganizations.addAll(subOrganizationService.getSubOrganizationAll(orgId));
        }
        if (timeSeriesScene == BizTimeSeriesScene.VOTER_PROGRESS_BY_AREA_DAILY) {
            AreaInitConfig areaInitConfig = coreWorkingAreaService.getAreaInitConfig(orgId);
            areaLevel = CoreAreaUtil.getLowerLevel(areaInitConfig.getRootAreas().get(0).getAreaLevel());
        }

        List<String> timePeriods = TimeFrameUtil.generateTimePeriods(timeSeriesScene.getTimeFrame(), nPrevTimeFrame);
        for (String timePeriod : timePeriods) {

            switch (timeSeriesScene) {
                case VOTER_PROGRESS_BY_CLUSTER_DAILY:
                    processGenerateTimeSeriesByCluster(orgId, timePeriod, subOrganizations);
                    break;

                case VOTER_PROGRESS_BY_AREA_DAILY:
                    processGenerateTimeSeriesByArea(orgId, timePeriod, areaLevel);
                    break;
            }
        }

        logData.add(",TIME_PERIODS="+ String.join("|", timePeriods));
    }

    private void processGenerateTimeSeriesByCluster(String orgId, String timePeriod, List<SubOrganization> subOrganizations) {
        Date periodDate = DateUtil.parseFormattedDate(timePeriod, DateUtil.FORMAT_DATE);
        String startDate = DateUtil.getFormattedDayStart(periodDate);
        String endDate = DateUtil.getFormattedDayEnd(periodDate);
        List<BizGroupQueryCount> groupQueryCounts = bizVoterDAO.countGroupBySubOrgWithinDate(orgId, startDate, endDate);
        for (BizGroupQueryCount groupQueryCount : groupQueryCounts) {

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    BizTimeSeriesScene timeSeriesScene = BizTimeSeriesScene.VOTER_PROGRESS_BY_CLUSTER_DAILY;
                    BizReportAccumulateTimeSeries accumulateTimeSeries = bizReportAccumulateTimeSeriesDAO
                            .getAndLock(orgId, timeSeriesScene, groupQueryCount.getGroupId());
                    if (accumulateTimeSeries == null) {
                        accumulateTimeSeries = new BizReportAccumulateTimeSeries();
                        accumulateTimeSeries.setReportTimeSeriesId(HashUtil.createHash(orgId, timeSeriesScene.getCode(), groupQueryCount.getGroupId()));
                        accumulateTimeSeries.setOrgId(orgId);
                        accumulateTimeSeries.setScene(timeSeriesScene);
                        accumulateTimeSeries.setSceneId(groupQueryCount.getGroupId());
                        accumulateTimeSeries.setSceneLabel(fetchSubOrgName(subOrganizations, groupQueryCount.getGroupId()));
                        accumulateTimeSeries.setTimeFrame(timePeriod);
                    }

                    accumulateTimeSeries.setAccumulateCount((int) groupQueryCount.getGroupCount());
                    accumulateTimeSeries.setModifiedTime(DateUtil.getCurrentFormattedDateMillis());

                    bizReportAccumulateTimeSeriesDAO.store(accumulateTimeSeries);
                }
            });

        }
    }

    private void processGenerateTimeSeriesByArea(String orgId, String timePeriod, CoreAreaLevel areaLevel) {
        Date periodDate = DateUtil.parseFormattedDate(timePeriod, DateUtil.FORMAT_DATE);
        String startDate = DateUtil.getFormattedDayStart(periodDate);
        String endDate = DateUtil.getFormattedDayEnd(periodDate);
        List<BizGroupQueryCount> groupQueryCounts = bizVoterDAO.countGroupByCoreAreaWithinDate(orgId, areaLevel, startDate, endDate);
        for (BizGroupQueryCount groupQueryCount : groupQueryCounts) {

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    BizTimeSeriesScene timeSeriesScene = BizTimeSeriesScene.VOTER_PROGRESS_BY_AREA_DAILY;
                    BizReportAccumulateTimeSeries accumulateTimeSeries = bizReportAccumulateTimeSeriesDAO
                            .getAndLock(orgId, timeSeriesScene, groupQueryCount.getGroupId());
                    if (accumulateTimeSeries == null) {
                        accumulateTimeSeries = new BizReportAccumulateTimeSeries();
                        accumulateTimeSeries.setReportTimeSeriesId(HashUtil.createHash(orgId, timeSeriesScene.getCode(), groupQueryCount.getGroupId()));
                        accumulateTimeSeries.setOrgId(orgId);
                        accumulateTimeSeries.setScene(timeSeriesScene);
                        accumulateTimeSeries.setSceneId(groupQueryCount.getGroupId());
                        accumulateTimeSeries.setSceneLabel(groupQueryCount.getGroupLabel());
                        accumulateTimeSeries.setTimeFrame(timePeriod);
                    }

                    accumulateTimeSeries.setAccumulateCount((int) groupQueryCount.getGroupCount());
                    accumulateTimeSeries.setModifiedTime(DateUtil.getCurrentFormattedDateMillis());

                    bizReportAccumulateTimeSeriesDAO.store(accumulateTimeSeries);
                }
            });

        }
    }

    private String fetchSubOrgName(List<SubOrganization> subOrganizations, String searchId) {
        for (SubOrganization subOrganization : subOrganizations) {
            if (StringUtil.equals(subOrganization.getSubOrgId(), searchId)) {
                return subOrganization.getName();
            }
        }
        return null;
    }
}