/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.biz.ezservice.model.admin.BizAdminAppData;
import id.ezclouds.biz.ezservice.model.admin.BizDashboardData;
import id.ezclouds.biz.ezservice.service.apibiz.admin.BizAdminService;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.digestlog.EmptyDigestLog;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.result.WebApiResult;
import id.ezclouds.core.shared.context.EzAppContextHolder;
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
 * @version $Id: WebApiController.java, v 0.1 2024‐02‐11 11:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizAdminService bizAdminService;

    @PostMapping(value = "/webapp/api/getAppData.json")
    private WebApiResult<BizAdminAppData> getAppData(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<BizAdminAppData> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_APP_DATA, result, new WebApiControllerTemplate.Handler<BizAdminAppData>() {
            @Override
            public void onRequestCheck() throws Exception {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.getAppData(sessionId);
            }

            @Override
            public BizAdminAppData convertResult(Object object) {
                if (object instanceof BizAdminAppData) {
                    return (BizAdminAppData) object;
                }
                return null;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/getDashboardData.json")
    private WebApiResult<List<BizDashboardData>> getDashboardData(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<BizDashboardData>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_DASHBOARD, result, new WebApiControllerTemplate.Handler<List<BizDashboardData>>() {
            @Override
            public void onRequestCheck() throws Exception {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.getDashboardData(sessionId);
            }

            @Override
            public List<BizDashboardData> convertResult(Object object) {
                if (object instanceof List) {
                    return (List<BizDashboardData>) object;
                }
                return null;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/getAppGallery.json")
    private WebApiResult<List<BizDashboardData>> getAppGallery(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<BizDashboardData>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_DASHBOARD, result, new WebApiControllerTemplate.Handler<List<BizDashboardData>>() {
            @Override
            public void onRequestCheck() throws Exception {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.getDashboardData(sessionId);
            }

            @Override
            public List<BizDashboardData> convertResult(Object object) {
                if (object instanceof List) {
                    return (List<BizDashboardData>) object;
                }
                return null;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

}