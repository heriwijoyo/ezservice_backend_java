/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.enums.BizProfileSection;
import id.ezclouds.biz.ezservice.enums.BizSwitchFlagObject;
import id.ezclouds.biz.ezservice.model.BizWhatsappLog;
import id.ezclouds.biz.ezservice.model.VideoCard;
import id.ezclouds.biz.ezservice.model.admin.*;
import id.ezclouds.biz.ezservice.model.event.AppEvent;
import id.ezclouds.biz.ezservice.model.news.BizWebDetailNews;
import id.ezclouds.biz.ezservice.model.news.BizWebSimpleNews;
import id.ezclouds.biz.ezservice.model.profile.BizCandidateProfile;
import id.ezclouds.biz.ezservice.model.profile.WebCandidateBio;
import id.ezclouds.biz.ezservice.service.apibiz.admin.BizAdminService;
import id.ezclouds.biz.ezservice.service.app.model.AppImageGallery;
import id.ezclouds.biz.ezservice.service.request.admin.BizAdminUploadRequest;
import id.ezclouds.biz.ezservice.service.request.web.*;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.core.shared.result.PageResult;
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
 * @version $Id: WebApiAdminController.java, v 0.1 2024‐02‐11 11:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebApiAdminController {

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
                BizWebUpdateItemRequest request = new BizWebUpdateItemRequest();
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

    @PostMapping(value = "/webapp/api/getNews.json")
    private WebApiPageResult<BizWebSimpleNews> getNews(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize ) {
        final WebApiPageResult<BizWebSimpleNews> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_NEWS_GET, result, new WebApiControllerTemplate.PageHandler<BizWebSimpleNews>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                return bizAdminService.getNews(request);
            }

            @Override
            public PageResult<BizWebSimpleNews> convertResult(Object object) {
                if (object instanceof PageResult) {
                    return (PageResult<BizWebSimpleNews>) object;
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

    @PostMapping(value = "/webapp/api/newsDetail.json")
    private WebApiResult<BizWebDetailNews> newsDetail(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "newsId", required = false) String newsId ) {
        final WebApiResult<BizWebDetailNews> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_NEWS_DETAIL, result, new WebApiControllerTemplate.Handler<BizWebDetailNews>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebDetailRequest<String> request = new BizWebDetailRequest<>();
                request.setSessionId(sessionId);
                request.setObject(newsId);
                return bizAdminService.getNewsDetail(request);
            }

            @Override
            public BizWebDetailNews convertResult(Object object) {
                if (object instanceof BizWebDetailNews) {
                    return (BizWebDetailNews) object;
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

    @PostMapping(value = "/webapp/api/newsFlagSwitch.json")
    private WebApiResult<String> newsFlagSwitch(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "itemId", required = false) String itemId,
            @RequestParam(name = "section", required = false) String section,
            @RequestParam(name = "value", required = false) String value ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_NEWS_FLAG_SWITCH, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebUpdateItemRequest request = new BizWebUpdateItemRequest();
                request.setSessionId(sessionId);
                request.setItemId(itemId);
                request.setSection(section);
                request.setValue(value);
                return bizAdminService.newsFlagSwitch(request);
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

    @PostMapping(value = "/webapp/api/events.json")
    private WebApiPageResult<AppEvent> events(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize ) {
        final WebApiPageResult<AppEvent> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_EVENT_GET, result, new WebApiControllerTemplate.PageHandler<AppEvent>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                return bizAdminService.getEvents(request);
            }

            @Override
            public PageResult<AppEvent> convertResult(Object object) {
                if (object instanceof PageResult) {
                    return (PageResult<AppEvent>) object;
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

    @PostMapping(value = "/webapp/api/eventDetail.json")
    private WebApiResult<AppEvent> eventDetail(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "eventId", required = false) String eventId ) {
        final WebApiResult<AppEvent> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_EVENT_DETAIL, result, new WebApiControllerTemplate.Handler<AppEvent>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebDetailRequest<String> request = new BizWebDetailRequest<>();
                request.setSessionId(sessionId);
                request.setObject(eventId);
                return bizAdminService.getEventDetail(request);
            }

            @Override
            public AppEvent convertResult(Object object) {
                if (object instanceof AppEvent) {
                    return (AppEvent) object;
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

    @PostMapping(value = "/webapp/api/eventFlagSwitch.json")
    private WebApiResult<String> eventFlagSwitch(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "itemId", required = false) String itemId,
            @RequestParam(name = "section", required = false) String section,
            @RequestParam(name = "value", required = false) String value ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_EVENT_FLAG_SWITCH, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebUpdateItemRequest request = new BizWebUpdateItemRequest();
                request.setSessionId(sessionId);
                request.setItemId(itemId);
                request.setSection(section);
                request.setValue(value);
                return bizAdminService.eventFlagSwitch(request);
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

    @PostMapping(value = "/webapp/api/videoCards.json")
    private WebApiPageResult<VideoCard> videoCards(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize ) {
        final WebApiPageResult<VideoCard> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_VIDEO_CARD_GET, result, new WebApiControllerTemplate.PageHandler<VideoCard>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                return bizAdminService.getVideoCards(request);
            }

            @Override
            public PageResult<VideoCard> convertResult(Object object) {
                if (object instanceof PageResult) {
                    return (PageResult<VideoCard>) object;
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

    @PostMapping(value = "/webapp/api/updateVideoCard.json")
    private WebApiResult<String> updateVideoCard(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "section", required = false) String section,
            @RequestParam(name = "sectionLabel", required = false) String sectionLabel,
            @RequestParam(name = "targetUrl", required = false) String targetUrl) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_VIDEO_CARD_UPDATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                VideoCard videoCard = new VideoCard();
                videoCard.setId(id);
                videoCard.setSection(section);
                videoCard.setSectionName(sectionLabel);
                videoCard.setTargetUrl(targetUrl);
                BizWebUpdateRequest<VideoCard> request = new BizWebUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setObject(videoCard);
                return bizAdminService.updateVideoCard(request);
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

    @PostMapping(value = "/webapp/api/adminCommonSwitchFlag.json")
    private WebApiResult<String> adminCommonSwitchFlag(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "objectType", required = false) String objectType,
            @RequestParam(name = "itemId", required = false) String itemId,
            @RequestParam(name = "section", required = false) String section,
            @RequestParam(name = "value", required = false) String value) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_COMMON_SWITCH_FLAG, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebUpdateItemRequest request = new BizWebUpdateItemRequest();
                request.setSessionId(sessionId);
                request.setItemId(itemId);
                request.setSection(section);
                request.setValue(value);
                return bizAdminService.adminCommonSwitchFlag(BizSwitchFlagObject.getByCode(objectType), request);
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

    @PostMapping(value = "/webapp/api/profileDetail.json")
    private WebApiResult<BizCandidateProfile> profileDetail(
            @RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<BizCandidateProfile> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_PROFILE_DETAIL, result, new WebApiControllerTemplate.Handler<BizCandidateProfile>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.getProfileDetail(sessionId);
            }

            @Override
            public BizCandidateProfile convertResult(Object object) {
                return (BizCandidateProfile) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/profileBioUpdate.json")
    private WebApiResult<String> profileBioUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "data", required = false) String data) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_PROFILE_UPDATE_BIO, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                List<WebCandidateBio> objectList = new ObjectMapper().readValue(data, new TypeReference<List<WebCandidateBio>>(){});
                BizWebCreateRequest<List<WebCandidateBio>> request = new BizWebCreateRequest<>();
                request.setSessionId(sessionId);
                request.setData(objectList);
                return bizAdminService.profileBioUpdate(request);
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

    @PostMapping(value = "/webapp/api/profileUpdate.json")
    private WebApiResult<String> profileUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "vision", required = false) String vision,
            @RequestParam(name = "mission", required = false) String mission,
            @RequestParam(name = "contactNumber", required = false) String contactNumber) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_PROFILE_UPDATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebCommonRequest request = new BizWebCommonRequest();
                request.setSessionId(sessionId);
                request.getExtendInfo().put(BizProfileSection.VISION.getCode(), vision);
                request.getExtendInfo().put(BizProfileSection.MISSION.getCode(), mission);
                request.getExtendInfo().put(BizProfileSection.CONTACT_NUMBER.getCode(), contactNumber);
                return bizAdminService.profileUpdate(request);
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

    @PostMapping(value = "/webapp/api/getWhatsappLog.json")
    private WebApiPageResult<BizWhatsappLog> whatsappLogs(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize ) {
        final WebApiPageResult<BizWhatsappLog> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_WHATSAPP_LOG, result, new WebApiControllerTemplate.PageHandler<BizWhatsappLog>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                return bizAdminService.getVideoCards(request);
            }

            @Override
            public PageResult<BizWhatsappLog> convertResult(Object object) {
                if (object instanceof PageResult) {
                    return (PageResult<BizWhatsappLog>) object;
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