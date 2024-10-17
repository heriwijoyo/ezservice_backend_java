/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.election.enums.BizProfileSection;
import id.ezclouds.biz.election.enums.BizSwitchFlagObject;
import id.ezclouds.biz.election.model.BizWhatsappLog;
import id.ezclouds.biz.election.model.VideoCard;
import id.ezclouds.common.model.admin.BizAdminAppData;
import id.ezclouds.biz.election.model.admin.BizDashboardData;
import id.ezclouds.biz.election.model.admin.BizMemberRequiredData;
import id.ezclouds.biz.election.service.request.web.BizWebCommonRequest;
import id.ezclouds.biz.election.service.request.web.BizWebCreateRequest;
import id.ezclouds.biz.election.service.request.web.BizWebPageRequest;
import id.ezclouds.biz.election.service.request.web.BizWebUpdateItemRequest;
import id.ezclouds.biz.election.model.event.AppEvent;
import id.ezclouds.biz.election.model.member.BizGender;
import id.ezclouds.biz.election.model.member.BizMember;
import id.ezclouds.biz.election.model.news.BizWebDetailNews;
import id.ezclouds.biz.election.model.news.BizWebSimpleNews;
import id.ezclouds.biz.election.model.profile.BizCandidateProfile;
import id.ezclouds.biz.election.model.profile.WebCandidateBio;
import id.ezclouds.biz.election.service.apibiz.admin.BizAdminService;
import id.ezclouds.biz.election.service.app.model.AppDocument;
import id.ezclouds.biz.election.service.app.model.AppImageGallery;
import id.ezclouds.biz.election.service.request.admin.BizAdminUploadRequest;
import id.ezclouds.common.facade.biz.admin.BizAdminConfigService;
import id.ezclouds.common.facade.biz.admin.BizAdminWhatsappService;
import id.ezclouds.common.model.request.admin.WebAdminRequest;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.request.admin.WebBizDetailRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.biz.election.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.organization.SubOrganization;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.result.WebApiPageResult;
import id.ezclouds.core.bifrost.app.web.result.WebApiResult;
import id.ezclouds.core.shared.model.LegacyCoreArea;
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

    @Autowired
    private BizAdminConfigService bizAdminConfigService;

    @Autowired
    private BizAdminWhatsappService bizAdminWhatsappService;

    @PostMapping(value = "/webapp/api/getAppData.json")
    private WebApiResult<BizAdminAppData> getAppData(
            @RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<BizAdminAppData> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_APP_DATA, result, new WebApiControllerTemplate.Handler<>() {
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
    private WebApiResult<List<BizDashboardData>> getDashboardData(
            @RequestParam(name = "sessionId", required = false) String sessionId) {
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
            @RequestParam(name = "value", required = false) String value) {
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
            @RequestParam(name = "pageSize", required = false) int pageSize) {
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
            @RequestParam(name = "newsId", required = false) String newsId) {
        final WebApiResult<BizWebDetailNews> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_NEWS_DETAIL, result, new WebApiControllerTemplate.Handler<BizWebDetailNews>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizDetailRequest<String> request = new WebBizDetailRequest<>();
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
            @RequestParam(name = "value", required = false) String value) {
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
            @RequestParam(name = "pageSize", required = false) int pageSize) {
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
            @RequestParam(name = "eventId", required = false) String eventId) {
        final WebApiResult<AppEvent> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_EVENT_DETAIL, result, new WebApiControllerTemplate.Handler<AppEvent>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizDetailRequest<String> request = new WebBizDetailRequest<>();
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
            @RequestParam(name = "value", required = false) String value) {
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
            @RequestParam(name = "pageSize", required = false) int pageSize) {
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
                WebBizUpdateRequest<VideoCard> request = new WebBizUpdateRequest<>();
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
            @RequestParam(name = "pageSize", required = false) int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        final WebApiPageResult<BizWhatsappLog> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_WHATSAPP_LOG, result, new WebApiControllerTemplate.PageHandler<BizWhatsappLog>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                request.setKeyword(keyword);
                return bizAdminService.getWhatsappLog(request);
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

    @PostMapping(value = "/webapp/api/resendWhatsapp.json")
    private WebApiResult<String> resendWhatsapp(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "mid", required = false) String messageId) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_RESEND_WHATSAPP, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebCreateRequest<String> request = new BizWebCreateRequest<>();
                request.setSessionId(sessionId);
                request.setData(messageId);
                return bizAdminService.resendWhatsapp(request);
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

    @PostMapping(value = "/webapp/api/getAppDocuments.json")
    private WebApiPageResult<AppDocument> getAppDocuments(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        final WebApiPageResult<AppDocument> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_APP_DOCUMENTS, result, new WebApiControllerTemplate.PageHandler<AppDocument>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                request.setKeyword(keyword);
                return bizAdminService.getAppDocuments(request);
            }

            @Override
            public PageResult<AppDocument> convertResult(Object object) {
                if (object instanceof PageResult) {
                    return (PageResult<AppDocument>) object;
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

    @PostMapping(value = "/webapp/api/subOrganizations.json")
    private WebApiPageResult<BizSubOrganization> getSubOrganizations(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        final WebApiPageResult<BizSubOrganization> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_SUB_ORGANIZATIONS, result, new WebApiControllerTemplate.PageHandler<BizSubOrganization>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizWebPageRequest request = new BizWebPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                request.setKeyword(keyword);
                return bizAdminService.getSubOrganizations(request);
            }

            @Override
            public PageResult<BizSubOrganization> convertResult(Object object) {
                if (object instanceof PageResult) {
                    return (PageResult<BizSubOrganization>) object;
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

    @PostMapping(value = "/webapp/api/subOrganizationAll.json")
    private WebApiResult<List<SubOrganization>> subOrganizationAll(
            @RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<List<SubOrganization>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_SUB_ORGANIZATION_ALL, result, new WebApiControllerTemplate.Handler<List<SubOrganization>>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.getSubOrganizationAll(sessionId);
            }

            @Override
            public List<SubOrganization> convertResult(Object object) {
                return (List<SubOrganization>) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/createSubOrganization.json")
    private WebApiResult<String> createSubOrganization(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "address", required = false) String address
    ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_CREATE_SUB_ORGANIZATION, result, new WebApiControllerTemplate.Handler<>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizSubOrganization subOrganization = new BizSubOrganization();
                subOrganization.setName(name);
                subOrganization.setAddress(address);

                BizWebCreateRequest<BizSubOrganization> bizRequest = new BizWebCreateRequest<>();
                bizRequest.setSessionId(sessionId);
                bizRequest.setData(subOrganization);

                return bizAdminService.createSubOrganization(bizRequest);
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

    @PostMapping(value = "/webapp/api/members.json")
    private WebApiPageResult<BizMember> getMembers(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize,
            @RequestParam(name = "searchScene", required = false) String searchScene,
            @RequestParam(name = "searchKeyword", required = false) String searchKeyword
    ) {
        final WebApiPageResult<BizMember> result = new WebApiPageResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_MEMBERS, result, new WebApiControllerTemplate.PageHandler<BizMember>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizPageRequest request = new WebBizPageRequest();
                request.setSessionId(sessionId);
                request.setPageNumber(pageNumber);
                request.setPageSize(pageSize);
                request.setSearchScene(searchScene);
                request.setSearchKeyword(searchKeyword);
                return bizAdminService.getMembersPage(request);
            }

            @Override
            public PageResult<BizMember> convertResult(Object object) {
                return (PageResult<BizMember>) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/memberAddRequiredData.json")
    private WebApiResult<BizMemberRequiredData> addMemberRequiredData(
            @RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<BizMemberRequiredData> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_MEMBER_REQUIRED_DATA, result, new WebApiControllerTemplate.Handler<BizMemberRequiredData>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizDetailRequest<String> request = new WebBizDetailRequest<>();
                request.setSessionId(sessionId);
                return bizAdminService.getMemberRequiredData(request);
            }

            @Override
            public BizMemberRequiredData convertResult(Object object) {
                if (object instanceof BizMemberRequiredData) {
                    return (BizMemberRequiredData) object;
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

    @PostMapping(value = "/webapp/api/memberAdd.json")
    private WebApiResult<String> addMember(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "subOrgId", required = false) String subOrgId,
            @RequestParam(name = "roles", required = false) String roles,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "dateOfBirth", required = false) String dateOfBirth,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "education", required = false) String education,
            @RequestParam(name = "occupation", required = false) String occupation,
            @RequestParam(name = "religion", required = false) String religion,
            @RequestParam(name = "ethnic", required = false) String ethnic,
            @RequestParam(name = "idCardNumber", required = false) String idCardNumber,
            @RequestParam(name = "provinceId", required = false) String provinceId,
            @RequestParam(name = "regencyId", required = false) String regencyId,
            @RequestParam(name = "districtId", required = false) String districtId,
            @RequestParam(name = "villageId", required = false) String villageId,
            @RequestParam(name = "rukunWarga", required = false) String rukunWarga,
            @RequestParam(name = "rukunTetangga", required = false) String rukunTetangga,
            @RequestParam(name = "tpsNo", required = false) String tpsNo
    ) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_CREATE_MEMBER, result, new WebApiControllerTemplate.Handler<>() {
            @Override
            public BizResult onProcess() throws Exception {
                BizMember bizMember = new BizMember();
                bizMember.setSubOrganization(new BizSubOrganization(subOrgId));
                bizMember.setRoles(roles);
                bizMember.setName(name);
                bizMember.setGender(BizGender.getByCode(gender));
                bizMember.setDateOfBirth(dateOfBirth);
                bizMember.setPhone(phone);
                bizMember.setEducation(education);
                bizMember.setOccupation(occupation);
                bizMember.setReligion(religion);
                bizMember.setEthnic(ethnic);
                bizMember.setIdCardNumber(idCardNumber);
                bizMember.setProvinceId(provinceId);
                bizMember.setRegencyId(regencyId);
                bizMember.setDistrictId(districtId);
                bizMember.setVillageId(villageId);
                bizMember.setRukunWarga(rukunWarga);
                bizMember.setRukunTetangga(rukunTetangga);
                bizMember.setTpsNumber(tpsNo);

                BizWebCreateRequest<BizMember> request = new BizWebCreateRequest<>();
                request.setSessionId(sessionId);
                request.setData(bizMember);
                return bizAdminService.createBizMember(request);
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

    @PostMapping(value = "/webapp/api/memberDetail.json")
    private WebApiResult<MemberBackOffice> memberDetail(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "memberId", required = false) String memberId) {
        final WebApiResult<MemberBackOffice> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_GET_MEMBER_DETAIL, result, new WebApiControllerTemplate.Handler<MemberBackOffice>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.getMemberBackOffice(sessionId, memberId);
            }

            @Override
            public MemberBackOffice convertResult(Object object) {
                return (MemberBackOffice) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/adminCoreArea.json")
    private WebApiResult<List<LegacyCoreArea>> coreArea(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "level", required = false) String level,
            @RequestParam(name = "parentId", required = false) String parentId) {
        final WebApiResult<List<LegacyCoreArea>> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_CORE_AREA, result, new WebApiControllerTemplate.Handler<List<LegacyCoreArea>>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.getCoreAreas(sessionId, level, parentId);
            }

            @Override
            public List<LegacyCoreArea> convertResult(Object object) {
                return (List<LegacyCoreArea>) object;
            }

            @Override
            public void onDigestLog(DigestLog digestLog) {
                DigestLogUtil.logWebDigest(LOGGER, digestLog);
            }
        });
        return result;
    }

    @PostMapping(value = "/webapp/api/csvUpload.json")
    private WebApiResult<String> csvUpload(
            @RequestPart("importFile") MultipartFile multipartFile,
            @RequestPart("sessionId") String sessionId,
            @RequestPart("subOrgId") String subOrgId) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_IMPORT_MEMBER_CSV, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.uploadMemberData(sessionId, subOrgId, multipartFile);
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

    @PostMapping(value = "/webapp/api/memberUpdateRoles.json")
    private WebApiResult<String> memberUpdateRoles(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "memberId", required = false) String memberId,
            @RequestParam(name = "roles", required = false) String roles) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_MEMBER_UPDATE_ROLES, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                return bizAdminService.memberUpdateRoles(sessionId, memberId, roles);
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

    @PostMapping(value = "/webapp/api/watzapNumberKey.json")
    private WebApiResult<String> watzapNumberKey(
            @RequestParam(name = "sessionId", required = false) String sessionId) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_WHATSAPP_NUMBER_KEY, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebAdminRequest request = new WebAdminRequest();
                request.setSessionId(sessionId);
                return bizAdminConfigService.getWatzapNumberKey(request);
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

    @PostMapping(value = "/webapp/api/watzapNumberKeyUpdate.json")
    private WebApiResult<String> watzapNumberKeyUpdate(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "numberKey", required = false) String numberKey) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_WHATSAPP_NUMBER_KEY_UPDATE, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizUpdateRequest<String> request = new WebBizUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setObject(numberKey);
                return bizAdminConfigService.updateWatzapNumberKey(request);
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

    @PostMapping(value = "/webapp/api/sendWhatsapp.json")
    private WebApiResult<String> sendWhatsapp(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "message", required = false) String message) {
        final WebApiResult<String> result = new WebApiResult<>();
        WebApiControllerTemplate.execute(WebEvent.WEB_API_WHATSAPP_SEND, result, new WebApiControllerTemplate.Handler<String>() {
            @Override
            public BizResult onProcess() throws Exception {
                WebBizUpdateRequest<String> request = new WebBizUpdateRequest<>();
                request.setSessionId(sessionId);
                request.setObject(message);
                return bizAdminWhatsappService.sendMessage(request);
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