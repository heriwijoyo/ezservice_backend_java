/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp.api;

import id.ezclouds.common.facade.biz.admin.BizAdminMasterDataService;
import id.ezclouds.common.model.biz.data.BizMasterDataOverall;
import id.ezclouds.common.model.biz.data.BizMasterDataUpdate;
import id.ezclouds.common.model.biz.data.VillageMasterData;
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

    @PostMapping(value = "/webapp/api/masterDataOverall.json")
    private WebApiResult<List<BizMasterDataOverall>> masterDataOverall(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<BizMasterDataOverall>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_MASTER_DATA_OVERALL, result, new WebApiControllerTemplate.Handler<List<BizMasterDataOverall>>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizPageRequest request = new WebBizPageRequest();
                request.setSessionId(sessionId);
                return bizAdminMasterDataService.getMasterDataOverall(request);
            }

            @Override
            public List<BizMasterDataOverall> convertResult(Object object) {
                return (List) object;
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

    @PostMapping(value = "/webapp/api/masterDataOverallUpdate.json")
    private WebApiResult<String> masterDataOverallUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "bizMasterId", required = false) String bizMasterId,
            @RequestParam(name = "value", required = false) String value) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_MASTER_DATA_OVERALL_UPDATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizUpdateRequest<BizMasterDataUpdate> request = new WebBizUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setObject(new BizMasterDataUpdate(bizMasterId, value));
                return bizAdminMasterDataService.updateMasterDataOverall(request);
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

    @PostMapping(value = "/webapp/api/masterDataAreaVillageUpdate.json")
    private WebApiResult<String> masterDataAreaVillageUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "bizMasterId", required = false) String bizMasterId,
            @RequestParam(name = "values", required = false) String values) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_MASTER_DATA_VILLAGE_UPDATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminMasterDataService
                        .updateMasterDataAreaVillage(sessionId, bizMasterId, values);
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