/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp.api;

import id.ezclouds.common.facade.biz.admin.BizAdminCoreAreaService;
import id.ezclouds.common.model.area.CoreArea;
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
 * @version $Id: WebAppApiCoreAreaController.java, v 0.1 2024‐09‐20 12:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebAppApiCoreAreaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizAdminCoreAreaService bizAdminCoreAreaService;

    @PostMapping(value = "/webapp/api/workingAreaDistricts.json")
    private WebApiResult<List<CoreArea>> getAreaDistricts(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<CoreArea>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_AREA_DISTRICTS, result, new WebApiControllerTemplate.Handler<List<CoreArea>>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminCoreAreaService.getWorkingAreaDistricts(sessionId);
            }

            @Override
            public List<CoreArea> convertResult(Object object) {
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