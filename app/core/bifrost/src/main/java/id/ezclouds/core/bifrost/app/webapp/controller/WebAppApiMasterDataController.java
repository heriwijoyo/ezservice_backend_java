/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp.controller;

import id.ezclouds.common.facade.biz.admin.BizAdminMasterDataService;
import id.ezclouds.common.model.biz.data.BizMasterDataUpdateNumber;
import id.ezclouds.common.model.biz.data.VillageMasterData;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.request.admin.WebBizDetailRequest;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.web.WebApiControllerTemplate;
import id.ezclouds.core.bifrost.app.web.result.WebApiResult;
import id.ezclouds.core.bifrost.app.webapp.event.WebAppApiEvent;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppApiMasterDataController.java, v 0.1 2024‐09‐17 11:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebAppApiMasterDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizAdminMasterDataService bizAdminMasterDataService;

    @PostMapping(value = "/webapp/api/reportOverall.json")
    private WebApiResult<List<List<String>>> reportOverall(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<List<String>>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_REPORT_OVERALL_GET, result, new WebApiControllerTemplate.Handler<List<List<String>>>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizPageRequest request = new WebBizPageRequest();
                request.setSessionId(sessionId);
                return bizAdminMasterDataService.getReportOverall(request);
            }

            @Override
            public List<List<String>> convertResult(Object object) {
                return (List) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/reportOverallUpdate.json")
    private WebApiResult<String> reportOverallUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "overallKey", required = false) String overallKey,
            @RequestParam(name = "overallValue", required = false) String overallValue) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_REPORT_OVERALL_UPDATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizReportOverall reportOverall = new BizReportOverall();
                reportOverall.setKeyId(overallKey);
                reportOverall.setCount(Integer.parseInt(overallValue));
                WebBizUpdateRequest<BizReportOverall> request = new WebBizUpdateRequest<>();
                request.setObject(reportOverall);
                request.setSessionId(sessionId);
                return bizAdminMasterDataService.reportOverallUpdate(request);
            }

            @Override
            public String convertResult(Object object) {
                return (String) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/masterDataAreaVillage.json")
    private WebApiResult<List<VillageMasterData>> masterDataAreaVillage(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "districtId", required = false) String districtId) {
        final WebApiResult<List<VillageMasterData>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_MASTER_DATA_VILLAGE, result, new WebApiControllerTemplate.Handler<List<VillageMasterData>>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizDetailRequest<String> request = new WebBizDetailRequest<>();
                request.setSessionId(sessionId);
                request.setObject(districtId);
                return bizAdminMasterDataService.getMasterDataAreaVillage(request);
            }

            @Override
            public List<VillageMasterData> convertResult(Object object) {
                return (List) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/masterDataAreaVillageUpdate.json")
    private WebApiResult<String> masterDataAreaVillageUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "bizMasterId", required = false) String bizMasterId,
            @RequestParam(name = "column", required = false) String column,
            @RequestParam(name = "value", required = false) Integer value) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_MASTER_DATA_VILLAGE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizMasterDataUpdateNumber dataUpdate = new BizMasterDataUpdateNumber(
                        bizMasterId, column, value
                );
                WebBizUpdateRequest<BizMasterDataUpdateNumber> request = new WebBizUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setObject(dataUpdate);

                return bizAdminMasterDataService.updateMasterDataAreaVillage(request);
            }

            @Override
            public String convertResult(Object object) {
                return (String) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }
}