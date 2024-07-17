/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.enums.BizImportScene;
import id.ezclouds.biz.ezservice.model.admin.*;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.service.apibiz.admin.BizSuperAdminService;
import id.ezclouds.biz.ezservice.service.app.model.BizAppConfig;
import id.ezclouds.biz.ezservice.service.request.BizDataImportRequest;
import id.ezclouds.biz.ezservice.service.request.web.*;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.core.shared.result.PageResult;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebApiAdminController.java, v 0.1 2024‐02‐11 11:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebApiSuperAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_API_CONTROLLER);

    @Autowired
    private BizSuperAdminService bizSuperAdminService;

    @PostMapping(value = "/webapp/api/getOrganization.json")
    private WebApiPageResult<BizOrganization> getOrganization(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize
    ) {
        final WebApiPageResult<BizOrganization> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_ORGANIZATION, result, new WebApiControllerTemplate.PageHandler<BizOrganization>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                return bizSuperAdminService.getOrganization(request);
            }

            @Override
            public PageResult<BizOrganization> convertResult(Object object) {
                if (object instanceof PageResult) {
                    return (PageResult<BizOrganization>) object;
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

    @PostMapping(value = "/webapp/api/createOrganization.json")
    private WebApiResult<String> createOrganization(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "orgCode", required = false) String orgCode,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "contactName", required = false) String contactName,
            @RequestParam(name = "contactPhone", required = false) String contactPhone,
            @RequestParam(name = "contactEmail", required = false) String contactEmail
    ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_CREATE_ORGANIZATION, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizOrganization organization = new BizOrganization();
                organization.setName(name);
                organization.setOrgId(orgId);
                organization.setCode(orgCode);
                organization.setAddress(address);
                organization.setContactName(contactName);
                organization.setContactPhone(contactPhone);
                organization.setContactEmail(contactEmail);

                BizWebCreateRequest<BizOrganization> request = new BizWebCreateRequest<>();
                request.setSessionId(sessionId);
                request.setData(organization);
                return bizSuperAdminService.createOrganization(request);
            }

            @Override
            public String convertResult(Object object) {
                if (object instanceof String) {
                    return (String) object;
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

    @PostMapping(value = "/webapp/api/getOrganizationDetail.json")
    private WebApiResult<BizOrganizationDetail> getOrganizationDetail(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId) {
        final WebApiResult<BizOrganizationDetail> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_ORGANIZATION_DETAIL, result, new WebApiControllerTemplate.Handler<BizOrganizationDetail>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebDetailRequest<String> request = new BizWebDetailRequest<>();
                request.setSessionId(sessionId);
                request.setObject(orgId);
                return bizSuperAdminService.getOrganizationDetail(request);
            }

            @Override
            public BizOrganizationDetail convertResult(Object object) {
                if (object instanceof BizOrganizationDetail) {
                    return (BizOrganizationDetail) object;
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

    @PostMapping(value = "/webapp/api/organizationUpdate.json")
    private WebApiResult<String> organizationUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "extendConfig", required = false) String extendConfig,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "contactName", required = false) String contactName,
            @RequestParam(name = "contactPhone", required = false) String contactPhone,
            @RequestParam(name = "contactEmail", required = false) String contactEmail,
            @RequestParam(name = "status", required = false) int status) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_UPDATE_ORGANIZATION, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizOrganization organization = new BizOrganization();
                organization.setOrgId(orgId);
                organization.setExtendConfig(extendConfig);
                organization.setAddress(address);
                organization.setContactName(contactName);
                organization.setContactPhone(contactPhone);
                organization.setContactEmail(contactEmail);
                organization.setStatus(status);

                BizWebUpdateRequest<BizOrganization> request = new BizWebUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setObject(organization);
                return bizSuperAdminService.updateOrganization(request);
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

    @PostMapping(value = "/webapp/api/appClientConfigUpdate.json")
    private WebApiResult<String> appConfigUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "appId", required = false) String appId,
            @RequestParam(name = "clientId", required = false) String clientId,
            @RequestParam(name = "clientSecret", required = false) String clientSecret,
            @RequestParam(name = "createdTime", required = false) String createdTime,
            @RequestParam(name = "status", required = false) int status ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_UPDATE_CLIENT_APP_CONFIG, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizApplicationConfig applicationConfig = new BizApplicationConfig();
                applicationConfig.setOrgId(orgId);
                applicationConfig.setId(id);
                applicationConfig.setAppId(appId);
                applicationConfig.setClientId(clientId);
                applicationConfig.setClientSecret(clientSecret);
                applicationConfig.setCreatedTime(createdTime);
                applicationConfig.setStatus(status);

                BizWebUpdateRequest<BizApplicationConfig> request = new BizWebUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setObject(applicationConfig);
                return bizSuperAdminService.updateAppConfig(request);
            }

            @Override
            public String convertResult(Object object) {
                if (object instanceof String) {
                    return (String) object;
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

    @PostMapping(value = "/webapp/api/appConfigUpdate.json")
    private WebApiResult<String> appConfigUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "mapData", required = false) String mapData ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_UPDATE_APP_CONFIG, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebUpdateRequest<List<BizAppConfig>> request = new BizWebUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setOrgId(orgId);
                request.setObject(new ObjectMapper().readValue(mapData, new TypeReference<List<BizAppConfig>>(){}));
                return bizSuperAdminService.updateBizAppConfig(request);
            }

            @Override
            public String convertResult(Object object) {
                if (object instanceof String) {
                    return (String) object;
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

    @PostMapping(value = "/webapp/api/coreOrgConfigUpdate.json")
    private WebApiResult<String> coreOrgConfigUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "mapData", required = false) String mapData ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_UPDATE_CORE_ORG_CONFIG, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebUpdateRequest<Map<String, String>> request = new BizWebUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setOrgId(orgId);
                request.setObject(new ObjectMapper().readValue(mapData, new TypeReference<Map<String, String>>(){}));
                return bizSuperAdminService.updateCoreOrgConfig(request);
            }

            @Override
            public String convertResult(Object object) {
                if (object instanceof String) {
                    return (String) object;
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

    @PostMapping(value = "/webapp/api/coreOrgAdminAdd.json")
    private WebApiResult<String> coreOrgAdminAdd(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "email", required = false) String email ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_CREATE_ADMIN_ORG, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizMember bizMember = new BizMember();
                bizMember.setName(name);
                bizMember.setPhone(phone);
                bizMember.setEmail(email);

                BizWebCreateRequest<BizMember> request = new BizWebCreateRequest<>();
                request.setSessionId(sessionId);
                request.setOrgId(orgId);
                request.setData(bizMember);
                return bizSuperAdminService.adminOrgCreateMember(request);
            }

            @Override
            public String convertResult(Object object) {
                if (object instanceof String) {
                    return (String) object;
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

    @PostMapping(value = "/webapp/api/whatsappSend.json")
    private WebApiResult<String> whatsappSend(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "orgId", required = false) String orgId,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "message", required = false) String message ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_WHATSAPP_SEND, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebCommonRequest request = new BizWebCommonRequest();
                request.setSessionId(sessionId);
                request.getExtendInfo().put("ORG_ID", orgId);
                request.getExtendInfo().put("PHONE", phone);
                request.getExtendInfo().put("MESSAGE", message);
                return bizSuperAdminService.adminWhatsappSendMessage(request);
            }

            @Override
            public String convertResult(Object object) {
                if (object instanceof String) {
                    return (String) object;
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

    @PostMapping(value = "/webapp/api/refreshAllCaches.json")
    private WebApiResult<List<String>> refreshAllCaches(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<String>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_REFRESH_ALL_CACHES, result, new WebApiControllerTemplate.Handler<List<String>>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizSuperAdminService.refreshAllCaches(sessionId);
            }

            @Override
            public List<String> convertResult(Object object) {
                return (List<String>) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/refreshAllDirectories.json")
    private WebApiResult<String> refreshAllDirectories(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_REFRESH_ALL_DIRECTORIES, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizSuperAdminService.refreshAllDirectories(sessionId);
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

    @PostMapping(value = "/webapp/api/refreshAllMenus.json")
    private WebApiResult<String> refreshAllMenus(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_REFRESH_ALL_MENUS, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizSuperAdminService.refreshAllMenus(sessionId);
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

    @PostMapping(value = "/webapp/api/commonImport.json")
    private WebApiResult<String> commonImport(@RequestPart("importFile") MultipartFile multipartFile, @RequestPart("postData") String postData) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_COMMON_IMPORT, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizDataImportRequest request = new BizDataImportRequest();
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode postDataNode = objectMapper.readTree(postData);
                    request.setSessionId(postDataNode.get("sessionId").asText());
                    request.setImportScene(BizImportScene.getByCode(postDataNode.get("scene").asText()));
                    request.setOrgId(postDataNode.get("orgId").asText());
                    request.setSubOrgId(postDataNode.get("subOrgId").asText());
                    request.setFileId(postDataNode.get("fileId").asText());
                    request.setMultipartFile(multipartFile);
                } catch (Exception e) {}

                return bizSuperAdminService.commonImport(request);
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

    @PostMapping(value = "/webapp/api/reloadReport.json")
    private WebApiResult<String> reloadReport(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_RELOAD_REPORT, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizSuperAdminService.reloadReport(sessionId);
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