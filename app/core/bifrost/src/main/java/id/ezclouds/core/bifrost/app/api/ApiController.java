/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.model.AppSetting;
import id.ezclouds.biz.ezservice.model.authentication.BizMemberCommonSession;
import id.ezclouds.biz.ezservice.model.news.SimpleNews;
import id.ezclouds.biz.ezservice.model.profile.CandidateProfile;
import id.ezclouds.biz.ezservice.model.profile.MemberProfile;
import id.ezclouds.biz.ezservice.service.result.BizMemberLoginResult;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.AppController;
import id.ezclouds.core.bifrost.app.api.digestlog.*;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.*;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.shared.result.ListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/api/candidate_profile.php")
    private ApiResult<CandidateProfile> getCandidateProfile(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_CANDIDATE_PROFILE, request, new RequestHandler<CandidateProfile>() {
            @Override
            public CandidateProfile convertResult(Object resultObject) {
                return (CandidateProfile) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<CandidateProfile> result) {
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

    @PostMapping(value = "/api/news.php")
    private ApiResult<ListResult<SimpleNews>> getNews(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_NEWS, request, new RequestHandler<ListResult<SimpleNews>>() {
            @Override
            public ListResult<SimpleNews> convertResult(Object resultObject) {
                return (ListResult<SimpleNews>) resultObject;
            }

            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<ListResult<SimpleNews>> result) {
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



    // ================ TRANSACTIONAL APIs ==================

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




    // ================ ADMIN APIs ==================

    @PostMapping(value = "/api/admin/create_web_session.json")
    private ApiResult<String> adminCreateWebSession(@RequestBody ApiRequest request) {
        return executeInTemplate(ApiEvent.API_ADMIN_CREATE_WEB_SESSION, request, new RequestHandler<String>() {
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