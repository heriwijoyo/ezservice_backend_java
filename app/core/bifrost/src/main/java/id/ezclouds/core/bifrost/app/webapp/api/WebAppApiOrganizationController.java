/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp.api;

import id.ezclouds.common.facade.biz.admin.BizAdminOrganizationService;
import id.ezclouds.common.model.core.organization.Organization;
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
 * @version $Id: WebAppApiOrganizationController.java, v 0.1 2024‐09‐23 1:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebAppApiOrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizAdminOrganizationService bizAdminOrganizationService;

    @PostMapping(value = "/webapp/api/organizations.json")
    private WebApiResult<List<Organization>> getOrganizations(
            @RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<Organization>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_ORGANIZATIONS, result, new WebApiControllerTemplate.Handler<>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminOrganizationService.getOrganizations(sessionId);
            }

            @Override
            public List<Organization> convertResult(Object object) {
                return (List) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/orgInitSystemSequence.json")
    private WebApiResult<String> orgInitSystemSequence(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_INIT_SYSTEM_SEQUENCE, result, new WebApiControllerTemplate.Handler<>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminOrganizationService.initSystemSequence(sessionId, orgId);
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

    @PostMapping(value = "/webapp/api/orgInitMigrateMember.json")
    private WebApiResult<String> orgInitMigrateMember(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "date", required = false) String date) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebAppApiEvent.WEBAPP_API_INIT_MIGRATE_MEMBER, result, new WebApiControllerTemplate.Handler<>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminOrganizationService.initMigrateMember(sessionId, orgId, date);
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