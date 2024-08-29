/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp.controller;

import id.ezclouds.common.facade.biz.admin.BizAdminMemberUpdateService;
import id.ezclouds.common.model.constant.MapKey;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppApiMemberController.java, v 0.1 2024‐08‐29 11:02 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebAppApiMemberController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizAdminMemberUpdateService bizAdminMemberUpdateService;

    @PostMapping(value = "/webapp/api/memberUpdateSubOrg.json")
    private WebApiResult<String> memberUpdateRoles(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "memberId", required = false) String memberId,
            @RequestParam(name = "subOrgId", required = false) String subOrgId) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_MEMBER_UPDATE_ROLES, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizUpdateRequest<Map<String, String>> request = new WebBizUpdateRequest<>(new HashMap<>());
                request.setSessionId(sessionId);
                request.getObject().put(MapKey.MEMBER_ID, memberId);
                request.getObject().put(MapKey.SUB_ORGANIZATION_ID, subOrgId);
                return bizAdminMemberUpdateService.updateMemberBackOffice(request);
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