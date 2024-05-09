/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.model.AppSetting;
import id.ezclouds.biz.ezservice.model.admin.BizAdminSession;
import id.ezclouds.biz.ezservice.model.app.AppMessage;
import id.ezclouds.biz.ezservice.model.app.SimpleAppMessage;
import id.ezclouds.biz.ezservice.model.authentication.BizMemberCommonSession;
import id.ezclouds.biz.ezservice.model.event.AppEventHome;
import id.ezclouds.biz.ezservice.model.member.BizMemberRegisterResult;
import id.ezclouds.biz.ezservice.model.news.BizNewsDetail;
import id.ezclouds.biz.ezservice.model.news.BizSimpleNews;
import id.ezclouds.biz.ezservice.model.profile.BizCandidateProfile;
import id.ezclouds.biz.ezservice.model.profile.MemberProfile;
import id.ezclouds.biz.ezservice.model.survey.BizSurveyForm;
import id.ezclouds.biz.ezservice.service.result.BizMemberLoginResult;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.AppController;
import id.ezclouds.core.bifrost.app.api.digestlog.*;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.*;
import id.ezclouds.core.bifrost.app.api.result.ApiPageResult;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.shared.model.CoreArea;
import id.ezclouds.core.shared.result.ListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping(value = "/")
    private void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
    }

    @PostMapping(value = "/api/setting.php")
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

    @PostMapping(value = "/api/survey_form.php")
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

    @PostMapping(value = "/api/candidate_profile.php")
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

    @PostMapping(value = "/api/profile.php")
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

    @PostMapping(value = "/api/news.json")
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

    @PostMapping(value = "/api/newsDetail.json")
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

    @PostMapping(value = "/api/messageMember.json")
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

    @PostMapping(value = "/api/messageMemberDetail.json")
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

    @PostMapping(value = "/api/appEvents.json")
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

    @PostMapping(value = "/api/session_check.php")
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

    @PostMapping(value = "/api/local_area.php")
    private ApiResult<List<CoreArea>> getLocalArea(@RequestBody LocalAreaRequest request) {
        return executeInTemplate(ApiEvent.API_GET_LOCAL_AREA, request, new RequestHandler<List<CoreArea>>() {
            @Override
            public List<CoreArea> convertResult(Object resultObject) {
                if (resultObject instanceof ArrayList) {
                    return (List<CoreArea>) resultObject;
                }
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<List<CoreArea>> result) {
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = "/api/subOrgGet.json")
    private ApiPageResult<BizSubOrganization> getSubOrganizations(@RequestBody ApiPageRequest request) {
        return executePageInTemplate(ApiEvent.API_GET_SUB_ORGANIZATIONS, request, new RequestHandler<BizSubOrganization>() {
            @Override
            public BizSubOrganization convertResult(Object resultObject) {
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizSubOrganization> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = "/api/memberGet.json")
    private ApiPageResult<BizSubOrganization> getMembers(@RequestBody ApiPageRequest request) {
        return executePageInTemplate(ApiEvent.API_GET_MEMBER, request, new RequestHandler<BizSubOrganization>() {
            @Override
            public BizSubOrganization convertResult(Object resultObject) {
                return null;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizSubOrganization> result) {
                return new SimpleDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }


    // ================ TRANSACTIONAL APIs ==================

    @PostMapping(value = "/api/member_register.json")
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
                return new EmptyDigestLog(result.isSuccess(), result.getResultCode());
            }
        });
    }

    @PostMapping(value = "/api/login.php")
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

    @PostMapping(value = "/api/logout.php")
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

    @PostMapping(value = "/api/update_password.php")
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

    @PostMapping(value = "/api/reset_password.php")
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

    @PostMapping(value = "/api/verify_otp.php")
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

    @PostMapping(value = "/api/member_upload.php", consumes = {MediaType.ALL_VALUE})
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

    @PostMapping(value = "/api/survey_submit.php")
    private ApiResult<String> surveySubmit(@RequestBody SurveySubmitRequest request) {
        return executeInTemplate(ApiEvent.API_SURVEY_SUBMIT, request, new RequestHandler<String>() {
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

    @PostMapping(value = "/api/subOrgCreate.json")
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


    // ================ ADMIN APIs ==================

    @PostMapping(value = "/api/admin/web_session_create.json")
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

    @PostMapping(value = "/api/admin/web_session.json")
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

    @PostMapping(value = "/api/admin/web_session_logout.json")
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

    @PostMapping(value = "/api/admin_upload.php", consumes = {MediaType.ALL_VALUE})
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

    @PostMapping(value = "/api/admin/memberUpdate.json")
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