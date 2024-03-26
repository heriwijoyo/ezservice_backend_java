/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.model.admin.BizAdminAppData;
import id.ezclouds.biz.ezservice.model.admin.BizDashboardData;
import id.ezclouds.biz.ezservice.model.admin.BizOrganization;
import id.ezclouds.biz.ezservice.service.apibiz.admin.BizAdminService;
import id.ezclouds.biz.ezservice.service.dataservice.model.AppImageGallery;
import id.ezclouds.biz.ezservice.service.request.admin.BizAdminUploadRequest;
import id.ezclouds.biz.ezservice.service.request.web.BizWebPageRequest;
import id.ezclouds.biz.ezservice.service.request.web.BizWebUpdateRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.result.PageResult;
import id.ezclouds.common.util.exception.ExceptionUtil;
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
    private WebApiPageResult<AppImageGallery> getAppGallery(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize
    ) {
        final WebApiPageResult<AppImageGallery> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_IMAGE_GALLERY, result, new WebApiControllerTemplate.PageHandler<AppImageGallery>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                return bizAdminService.getAppGallery(request);
            }

            @Override
            public PageResult<AppImageGallery> convertResult(Object object) {
                if (object instanceof PageResult) {
                    return (PageResult<AppImageGallery>) object;
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

    @PostMapping(value = "/webapp/api/updateAppGallery.json")
    private WebApiResult<String> updateAppGallery(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "itemId", required = false) String itemId,
            @RequestParam(name = "section", required = false) String section,
            @RequestParam(name = "value", required = false) String value
    ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_UPDATE_IMAGE_GALLERY, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebUpdateRequest request = new BizWebUpdateRequest();
                request.setSessionId(sessionId);
                request.setItemId(itemId);
                request.setSection(section);
                request.setValue(value);
                return bizAdminService.updateAppGallery(request);
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

    @PostMapping(value = "/webapp/api/adminCommonPost.json")
    private WebApiResult<String> adminUpload(@RequestPart("imageFile") MultipartFile multipartFile, @RequestPart("postData") String postData) {
        WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.ADMIN_COMMON_POST_WITH_FILE_UPLOAD, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizAdminUploadRequest uploadRequest = composeUploadRequest(multipartFile, postData);
                return bizAdminService.adminCommonPostWithFileUpload(uploadRequest);
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
                return bizAdminService.getOrganization(request);
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

    @PostMapping(value = "/webapp/api/refreshAllCaches.json")
    private WebApiResult<List<String>> refreshAllCaches(@RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<String>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_REFRESH_ALL_CACHES, result, new WebApiControllerTemplate.Handler<List<String>>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.refreshAllCaches(sessionId);
            }

            @Override
            public List<String> convertResult(Object object) {
                if (object instanceof List) {
                    return (List<String>) object;
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

    private BizAdminUploadRequest composeUploadRequest(MultipartFile multipartFile, String postData) {
        BizAdminUploadRequest request;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            request = objectMapper.readValue(postData, BizAdminUploadRequest.class);
        } catch (Exception e) {
            LOGGER.error(ExceptionUtil.getStackTrace(e));
            request = new BizAdminUploadRequest();
        }
        request.setMultipartFile(multipartFile);

        return request;
    }
}