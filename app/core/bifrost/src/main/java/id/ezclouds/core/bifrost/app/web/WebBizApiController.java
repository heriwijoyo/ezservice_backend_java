/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.common.facade.admin.BizAdminConfigService;
import id.ezclouds.common.model.request.admin.CommonTableCreateRequest;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.result.WebApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizApiController.java, v 0.1 2024‐08‐17 3:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebBizApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizAdminConfigService bizAdminConfigService;

    @PostMapping(value = "/webapp/api/biz/commonTableCreate.json")
    private WebApiResult<String> createCommonTable(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "column", required = false) String column,
            @RequestParam(name = "config", required = false) String config) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_BIZ_COMMON_TABLE_CREATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                CommonTableCreateRequest request = new CommonTableCreateRequest();
                request.setOrgId(orgId);
                bizAdminConfigService.createBizCommonTable(null);

                return null;
            }

            @Override
            public String convertResult(Object object) {
                return null;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {

            }
        });
        return result;
    }
}