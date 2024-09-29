/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.election.model.AppSetting;
import id.ezclouds.biz.election.model.admin.BizAdminSession;
import id.ezclouds.biz.election.model.app.AppMessage;
import id.ezclouds.biz.election.model.app.SimpleAppMessage;
import id.ezclouds.biz.election.model.authentication.BizMemberCommonSession;
import id.ezclouds.biz.election.model.event.AppEventHome;
import id.ezclouds.biz.election.model.member.BizMemberRegisterResult;
import id.ezclouds.biz.election.model.news.BizNewsDetail;
import id.ezclouds.biz.election.model.news.BizSimpleNews;
import id.ezclouds.biz.election.model.profile.BizCandidateProfile;
import id.ezclouds.biz.election.model.profile.MemberProfile;
import id.ezclouds.biz.election.model.survey.BizSurveyForm;
import id.ezclouds.biz.election.service.app.model.AppDocument;
import id.ezclouds.biz.election.service.result.BizMemberLoginResult;
import id.ezclouds.biz.election.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.common.model.request.api.ApiRequest;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.AppController;
import id.ezclouds.core.bifrost.app.api.digestlog.*;
import id.ezclouds.common.model.request.api.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.*;
import id.ezclouds.common.model.result.api.ApiPageResult;
import id.ezclouds.common.model.result.api.ApiResult;
import id.ezclouds.common.model.result.api.BizApiPageResult;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.shared.model.LegacyCoreArea;
import id.ezclouds.core.shared.result.ListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiController.java, v 0.1 2023‐12‐03 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class ApiController extends AppController {

    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(CommonLoggerConstant.API_CONTROLLER);
    }

    @PostMapping(value = {"/api/setting.php", "/api/v2/setting.json"})
    private ApiResult<AppSetting> getSetting(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_APP_SETTING, request, new RequestHandler<AppSetting>() {
            @Override
            public AppSetting convertResult(Object resultObject) {
                return (AppSetting) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<AppSetting> result) {
                EmptyDigestLog digestLog = new EmptyDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, toEmptyResult(result));
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/survey_form.php", "/api/v2/survey/form.json"})
    private ApiResult<BizSurveyForm> getSurveyForm(@RequestBody SurveyFormRequest request) {
        return executeInTemplate(ApiEvent.API_SURVEY_FORM, request, new RequestHandler<BizSurveyForm>() {
            @Override
            public BizSurveyForm convertResult(Object resultObject) {
                if (resultObject instanceof BizSurveyForm) {
                    return (BizSurveyForm) resultObject;
                }
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizSurveyForm> result) {
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/candidate_profile.php", "/api/v2/profile/candidate.json"})
    private ApiResult<BizCandidateProfile> getCandidateProfile(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_CANDIDATE_PROFILE, request, new RequestHandler<BizCandidateProfile>() {
            @Override
            public BizCandidateProfile convertResult(Object resultObject) {
                return (BizCandidateProfile) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizCandidateProfile> result) {
                EmptyDigestLog digestLog = new EmptyDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, toEmptyResult(result));
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/profile.php", "/api/v2/profile/member.json"})
    private ApiResult<MemberProfile> getMemberProfile(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_MEMBER_PROFILE, request, new RequestHandler<MemberProfile>() {
            @Override
            public MemberProfile convertResult(Object resultObject) {
                return (MemberProfile) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<MemberProfile> result) {
                EmptyDigestLog digestLog = new EmptyDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, toEmptyResult(result));
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/news.json", "/api/v2/news/summary.json"})
    private ApiPageResult<BizSimpleNews> getNews(@RequestBody ApiPageRequest request) {
        return executePageInTemplate(ApiEvent.API_NEWS, request, new RequestHandler<BizSimpleNews>() {
            @Override
            public BizSimpleNews convertResult(Object resultObject) {
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizSimpleNews> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/newsDetail.json", "/api/v2/news/detail.json"})
    private ApiResult<BizNewsDetail> getNewsDetail(@RequestBody NewsDetailRequest request) {
        return executeInTemplate(ApiEvent.API_NEWS_DETAIL, request, new RequestHandler<BizNewsDetail>() {
            @Override
            public BizNewsDetail convertResult(Object resultObject) {
                return (BizNewsDetail) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizNewsDetail> result) {
                EmptyDigestLog digestLog = new EmptyDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, toEmptyResult(result));
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/messageMember.json", "/api/v2/inbox/member/summary.json"})
    private ApiPageResult<SimpleAppMessage> messageMember(@RequestBody ApiPageRequest request) {
        return executePageInTemplate(ApiEvent.API_MESSAGE_MEMBER, request, new RequestHandler<SimpleAppMessage>() {
            @Override
            public SimpleAppMessage convertResult(Object resultObject) {
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<SimpleAppMessage> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/messageMemberDetail.json", "/api/v2/inbox/member/detail.json"})
    private ApiResult<AppMessage> messageMemberDetail(@RequestBody ApiDetailRequest request) {
        return executeInTemplate(ApiEvent.API_MESSAGE_MEMBER_DETAIL, request, new RequestHandler<AppMessage>() {
            @Override
            public AppMessage convertResult(Object resultObject) {
                return (AppMessage) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<AppMessage> result) {
                EmptyDigestLog digestLog = new EmptyDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, toEmptyResult(result));
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/appEvents.json", "/api/v2/event/summary.json"})
    private ApiResult<AppEventHome> appEvents(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_APP_EVENT, request, new RequestHandler<AppEventHome>() {
            @Override
            public AppEventHome convertResult(Object resultObject) {
                return (AppEventHome) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<AppEventHome> result) {
                EmptyDigestLog digestLog = new EmptyDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, toEmptyResult(result));
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/session_check.php", "/api/v2/session/member/validate.json"})
    private ApiResult<String> sessionCheck(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_SESSION_CHECK, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return (String) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                EmptyDigestLog digestLog = new EmptyDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, toEmptyResult(result));
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/local_area.php", "/api/v2/area/localArea.json"})
    private ApiResult<List<LegacyCoreArea>> getLocalArea(@RequestBody LocalAreaRequest request) {
        return executeInTemplate(ApiEvent.API_GET_LOCAL_AREA, request, new RequestHandler<List<LegacyCoreArea>>() {
            @Override
            public List<LegacyCoreArea> convertResult(Object resultObject) {
                if (resultObject instanceof ArrayList) {
                    return (List<LegacyCoreArea>) resultObject;
                }
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<List<LegacyCoreArea>> result) {
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/subOrganizations.json", "/api/v2/organization/subOrganizations.json"})
    private ApiResult<List<BizSubOrganization>> subOrganizations(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_GET_SUB_ORGANIZATIONS, request, new RequestHandler<List<BizSubOrganization>>() {
            @Override
            public List<BizSubOrganization> convertResult(Object resultObject) {
                if (resultObject instanceof  ArrayList) {
                    return (List<BizSubOrganization>) resultObject;
                }
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<List<BizSubOrganization>> result) {
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/getSubOrg.json", "/api/v2/organization/pageSubOrganizations.json"})
    private BizApiPageResult<BizSubOrganization> getSubOrg(@RequestBody ApiPageRequest request) {
        return ApiControllerTemplate.execute(ApiEvent.API_PAGE_SUB_ORGANIZATIONS, request, new ApiControllerTemplate.Handler<BizSubOrganization>() {
            @Override
            public BizSubOrganization convertItem(Object object) {
                return (BizSubOrganization) object;
            }

            @Override
            public DigestLog composeDigestLog(ApiPageRequest request, BizApiPageResult<BizSubOrganization> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/getMembers.json", "/api/v2/member/summary.json"})
    private BizApiPageResult<CoreMember> getMembers(@RequestBody ApiPageRequest request) {
        return ApiControllerTemplate.execute(ApiEvent.API_PAGE_MEMBER, request, new ApiControllerTemplate.Handler<CoreMember>() {
            @Override
            public CoreMember convertItem(Object object) {
                return (CoreMember)object;
            }

            @Override
            public DigestLog composeDigestLog(ApiPageRequest request, BizApiPageResult<CoreMember> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/appDocuments.json", "/api/v2/document/appDocuments.json"})
    private BizApiPageResult<AppDocument> getAppDocuments(@RequestBody ApiPageRequest request) {
        return ApiControllerTemplate.execute(ApiEvent.API_PAGE_APP_DOCUMENTS, request, new ApiControllerTemplate.Handler<AppDocument>() {
            @Override
            public AppDocument convertItem(Object object) {
                return (AppDocument) object;
            }

            @Override
            public DigestLog composeDigestLog(ApiPageRequest request, BizApiPageResult<AppDocument> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }


    // ================ TRANSACTIONAL APIs ==================

    @PostMapping(value = {"/api/memberRegister.json", "/api/v2/member/register.json"})
    private ApiResult<BizMemberRegisterResult> memberRegister(@RequestBody MemberRegisterRequest request) {
        return executeInTemplate(ApiEvent.API_MEMBER_REGISTER, request, new RequestHandler<BizMemberRegisterResult>() {
            @Override
            public BizMemberRegisterResult convertResult(Object resultObject) {
                if (resultObject instanceof BizMemberRegisterResult) {
                    return (BizMemberRegisterResult) resultObject;
                }
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizMemberRegisterResult> result) {
                MemberRegisterDigestLog digestLog = new MemberRegisterDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/login.php", "/api/v2/authentication/loginMember.json"})
    private ApiResult<BizMemberLoginResult> memberLogin(@RequestBody MemberLoginRequest request) {
        return executeInTemplate(ApiEvent.API_MEMBER_LOGIN, request, new RequestHandler<BizMemberLoginResult>() {
            @Override
            public BizMemberLoginResult convertResult(Object resultObject) {
                return (BizMemberLoginResult) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizMemberLoginResult> result) {
                MemberLoginDigestLog digestLog = new MemberLoginDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/logout.php", "/api/v2/authentication/logoutMember.json"})
    private ApiResult<Void> memberLogout(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_MEMBER_LOGOUT, request, new RequestHandler<Void>() {
            @Override
            public Void convertResult(Object resultObject) {
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<Void> result) {
                EmptyDigestLog digestLog = new EmptyDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/update_password.php", "/api/v2/account/updatePassword.json"})
    private ApiResult<String> updatePassword(@RequestBody MemberUpdatePasswordRequest request) {
        return executeInTemplate(ApiEvent.API_MEMBER_UPDATE_PASSWORD, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return (String) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                MemberUpdatePasswordDigestLog digestLog = new MemberUpdatePasswordDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/reset_password.php", "/api/v2/account/resetPassword.json"})
    private ApiResult<BizMemberCommonSession> resetPassword(@RequestBody MemberResetPasswordRequest request) {
        return executeInTemplate(ApiEvent.API_MEMBER_RESET_PASSWORD, request, new RequestHandler<BizMemberCommonSession>() {
            @Override
            public BizMemberCommonSession convertResult(Object resultObject) {
                return (BizMemberCommonSession) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizMemberCommonSession> result) {
                MemberResetPasswordDigestLog digestLog = new MemberResetPasswordDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/verify_otp.php", "/api/v2/account/verifyOtp.json"})
    private ApiResult<String> verifyCommonSession(@RequestBody VerifyCommonSessionRequest request) {
        return executeInTemplate(ApiEvent.API_MEMBER_VERIFY_COMMON_SESSION, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return (String) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                SimpleDigestLog digestLog = new SimpleDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/memberUpload.json", "/api/v2/media/upload.json"}, consumes = {MediaType.ALL_VALUE})
    private ApiResult<String> memberUpload(@RequestPart("mediaFile") MultipartFile mediaFile, @RequestPart("postData") String postData) throws Exception {

        MemberUploadRequest request = convertPostData(postData);

        return executeInTemplate(ApiEvent.API_MEMBER_UPLOAD_MEDIA, request, mediaFile, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return "OK";
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                SimpleDigestLog digestLog = new SimpleDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/survey_submit.php", "/api/v2/survey/submit.json"})
    private ApiResult<String> surveySubmit(@RequestBody SurveySubmitRequest request) {
        return executeInTemplate(ApiEvent.API_SURVEY_SUBMIT, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return (String) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/subOrgCreate.json", "/api/v2/organization/subOrganizationCreate.json"})
    private ApiResult<String> subOrgCreate(@RequestBody SubOrgCreateRequest request) {
        return executeInTemplate(ApiEvent.API_SUB_ORG_CREATE, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                if (resultObject instanceof String) {
                    return (String) resultObject;
                }
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/asyncProcessTrigger.json", "/api/v2/async/processTrigger.json"})
    private ApiResult<String> asyncProcessTrigger(@RequestBody AsyncTriggerRequest request) {
        return executeInTemplate(ApiEvent.API_ASYNC_PROCESS_TRIGGER, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return (String) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }


    // ================ ADMIN APIs ==================

    @PostMapping(value = {"/api/admin/web_session_create.json", "/api/v2/admin/webSessionCreate.json"})
    private ApiResult<BizAdminSession> adminCreateWebSession(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_ADMIN_CREATE_WEB_SESSION, request, new RequestHandler<BizAdminSession>() {
            @Override
            public BizAdminSession convertResult(Object resultObject) {
                return (BizAdminSession) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizAdminSession> result) {
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/admin/web_session.json", "/api/v2/admin/webSessions.json"})
    private ApiResult<ListResult<BizAdminSession>> adminGetWebSession(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_ADMIN_GET_WEB_SESSION, request, new RequestHandler<ListResult<BizAdminSession>>() {
            @Override
            public ListResult<BizAdminSession> convertResult(Object resultObject) {
                return (ListResult<BizAdminSession>) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<ListResult<BizAdminSession>> result) {
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/admin/web_session_logout.json", "/api/v2/admin/webSessionLogout.json"})
    private ApiResult<String> adminLogoutWebSession(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_ADMIN_LOGOUT_WEB_SESSION, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return (String) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = {"/api/admin_upload.php", "/api/v2/admin/upload.json"}, consumes = {MediaType.ALL_VALUE})
    private ApiResult<String> adminUpload(@RequestPart("mediaFile") MultipartFile mediaFile, @RequestPart("postData") String postData) throws Exception {

        AdminUploadRequest request = convertAdminPostData(postData);

        return executeInTemplate(ApiEvent.API_ADMIN_UPLOAD_MEDIA, request, mediaFile, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return "OK";
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                SimpleDigestLog digestLog = new SimpleDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @PostMapping(value = {"/api/admin/memberUpdate.json", "/api/v2/admin/memberUpdate.json"})
    private ApiResult<String> memberUpdate(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_ADMIN_MEMBER_UPDATE, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return (String) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    private MemberUploadRequest convertPostData(String postData) {
        ObjectMapper objectMapper = new ObjectMapper();
        MemberUploadRequest memberUploadRequest;
        try {
            memberUploadRequest = objectMapper.readValue(postData, MemberUploadRequest.class);
        } catch (Exception exception) {
            memberUploadRequest = null;
            getLogger().error(ExceptionUtil.getStackTrace(exception));
        }
        return memberUploadRequest;
    }

    private AdminUploadRequest convertAdminPostData(String postData) {
        ObjectMapper objectMapper = new ObjectMapper();
        AdminUploadRequest adminUploadRequest;
        try {
            adminUploadRequest = objectMapper.readValue(postData, AdminUploadRequest.class);
        } catch (Exception exception) {
            adminUploadRequest = null;
            getLogger().error(ExceptionUtil.getStackTrace(exception));
        }
        return adminUploadRequest;
    }

    private <T> ApiResult<Void> toEmptyResult(ApiResult<T> apiResult) {
        ApiResult<Void> emptyResult = new ApiResult<>();
        emptyResult.setSuccess(apiResult.isSuccess());
        emptyResult.setErrorResult(apiResult.getErrorResult());
        return emptyResult;
    }
}