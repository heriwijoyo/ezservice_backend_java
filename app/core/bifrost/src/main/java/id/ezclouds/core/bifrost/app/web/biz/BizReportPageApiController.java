/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.biz;

import id.ezclouds.common.facade.biz.report.BizReportSubOrganizationService;
import id.ezclouds.common.facade.biz.report.BizReportSurveyService;
import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.web.WebApiControllerTemplate;
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
 * @version $Id: BizReportPageApiController.java, v 0.1 2024‐10‐30 2:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class BizReportPageApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizReportSubOrganizationService bizReportSubOrganizationService;

    @Autowired
    private BizReportSurveyService bizReportSurveyService;

    @PostMapping(value = "/biz/api/report/subOrganizations.json")
    private WebApiResult<List<SubOrganization>> getActiveSubOrganizations(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<SubOrganization>> result = new WebApiResult<>();

        WebApiControllerTemplate.execute(BizReportWebApiEvent.BIZ_REPORT_WEB_API_GET_SUB_ORGANIZATIONS, result, new WebApiControllerTemplate.Handler<>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizReportSubOrganizationService.getActiveSubOrganizations(sessionId);
            }

            @Override
            public List<SubOrganization> convertResult(Object object) {
                return (List) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });

        return result;
    }

    @PostMapping(value = "/biz/api/report/surveyRecap.json")
    private WebApiResult<List<List<String>>> getSurveyRecap(@RequestParam(name = "secretToken") String secretToken) {
        final WebApiResult<List<List<String>>> result = new WebApiResult<>();

        WebApiControllerTemplate.execute(BizReportWebApiEvent.BIZ_REPORT_WEB_API_GET_SURVEY_RECAP, result, new WebApiControllerTemplate.Handler<>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizReportSurveyService.getSurveyRecap(secretToken);
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