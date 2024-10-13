/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.init;

import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateAreaDAO;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.biz.report.BizReportAccumulateArea;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessInitReportAccumulateArea.java, v 0.1 2024‐10‐08 3:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessInitReportAccumulateArea extends BizAsyncProcessor {

    @Autowired
    private CoreWorkingAreaService coreWorkingAreaService;

    @Autowired
    private BizReportAccumulateAreaDAO bizReportAccumulateAreaDAO;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.INIT_REPORT_ACCUMULATE_AREA;
    }

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);
        List<CoreArea> allCoreAreas = coreWorkingAreaService.fetchAllCoreAreas(orgId);
        logData.add(",ALL_CORE_AREAS="+ allCoreAreas.size());

        String currentTime = DateUtil.getCurrentFormattedDateMillis();

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                for (CoreArea coreArea : allCoreAreas) {
                    BizReportAccumulateArea accumulateArea = bizReportAccumulateAreaDAO
                            .getAndLock(orgId, coreArea.getAreaLevel(), coreArea.getAreaId());

                    if (accumulateArea == null) {
                        accumulateArea = new BizReportAccumulateArea(coreArea);
                        accumulateArea.setOrgId(orgId);
                    }
                    else {
                        switch (coreArea.getAreaLevel()) {
                            case VILLAGE:
                                accumulateArea.setDistrictId(coreArea.getParentId());
                                break;
                            case DISTRICT:
                                accumulateArea.setRegencyId(coreArea.getParentId());
                                break;
                            case REGENCY:
                                accumulateArea.setProvinceId(coreArea.getParentId());
                                break;
                        }
                    }

                    accumulateArea.setModifiedTime(currentTime);

                    bizReportAccumulateAreaDAO.store(accumulateArea);
                }
            }
        });

        return true;
    }
}