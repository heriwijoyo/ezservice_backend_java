/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.request.admin.WebBizDetailRequest;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.biz.admin.BizAdminConfigService;
import id.ezclouds.common.model.request.admin.CommonTableCreateRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.model.util.WebBizPageRequestFactory;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.result.WebApiPageResult;
import id.ezclouds.core.bifrost.app.web.result.WebApiResult;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizAdminApiController.java, v 0.1 2024‐08‐17 3:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebBizAdminApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizAdminConfigService bizAdminConfigService;

    @PostMapping(value = "/webapp/api/biz/commonTableCreate.json")
    private WebApiResult<String> createCommonTable(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "columns", required = false) String columns,
            @RequestParam(name = "config", required = false) String config) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_BIZ_COMMON_TABLE_CREATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                CommonTableCreateRequest request = new CommonTableCreateRequest();
                request.setSessionId(sessionId);
                request.setOrgId(orgId);
                request.setCode(code);
                request.setTitle(title);
                request.setColumns(columns);
                request.setConfig(config);
                return bizAdminConfigService.createBizCommonTable(request);
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

    @PostMapping(value = "/webapp/api/biz/commonTableUpdate.json")
    private WebApiResult<String> updateCommonTable(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "tableId", required = false) String tableId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "columns", required = false) String columns,
            @RequestParam(name = "config", required = false) String config) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_BIZ_COMMON_TABLE_UPDATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizCommonTable commonTable = new BizCommonTable();
                commonTable.setTableId(tableId);
                commonTable.setOrgId(orgId);
                commonTable.setCode(code);
                commonTable.setTitle(title);
                commonTable.setColumns(columns);
                commonTable.setConfig(config);

                WebBizUpdateRequest<BizCommonTable> request = new WebBizUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setObject(commonTable);
                return bizAdminConfigService.updateBizCommonTable(request);
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

    @PostMapping(value = "/webapp/api/biz/commonTables.json")
    private WebApiPageResult<BizCommonTable> commonTables(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize,
            @RequestParam(name = "searchScene", required = false) String searchScene,
            @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        final WebApiPageResult<BizCommonTable> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_BIZ_COMMON_TABLES, result, new WebApiControllerTemplate.PageHandler<BizCommonTable>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizPageRequest request = WebBizPageRequestFactory
                        .createRequest(
                                sessionId,
                                pageNumber,
                                pageSize,
                                searchScene,
                                searchKeyword
                        );
                return bizAdminConfigService.getBizCommonTables(request);
            }

            @Override
            public PageResult<BizCommonTable> convertResult(Object object) {
                return (PageResult<BizCommonTable>) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/biz/commonTable.json")
    private WebApiResult<BizCommonTable> commonTable(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "detailId", required = false) String detailId) {
        final WebApiResult<BizCommonTable> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_BIZ_COMMON_TABLE_DETAIL, result, new WebApiControllerTemplate.Handler<BizCommonTable>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizDetailRequest<String> request = new WebBizDetailRequest<>();
                request.setSessionId(sessionId);
                request.setObject(detailId);
                return bizAdminConfigService.getBizCommonTable(request);
            }

            @Override
            public BizCommonTable convertResult(Object object) {
                return (BizCommonTable) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }
}