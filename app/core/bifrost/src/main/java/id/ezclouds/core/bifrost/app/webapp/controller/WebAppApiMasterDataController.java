/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp.controller;

import id.ezclouds.common.facade.biz.admin.BizAdminMasterDataService;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.web.WebApiControllerTemplate;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.result.WebApiResult;
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
    private WebApiResult<List<List<String>>> getMasterData(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<List<String>>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_MASTER_DATA, result, new WebApiControllerTemplate.Handler<List<List<String>>>() {
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
}