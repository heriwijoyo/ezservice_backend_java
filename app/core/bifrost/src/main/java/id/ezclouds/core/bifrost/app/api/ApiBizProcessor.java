/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.service.apibiz.*;
import id.ezclouds.biz.ezservice.service.apibiz.BizAuthService;
import id.ezclouds.biz.ezservice.service.apibiz.admin.BizAdminService;
import id.ezclouds.biz.ezservice.service.dataservice.request.BizSubOrgCreateRequest;
import id.ezclouds.biz.ezservice.service.request.*;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.SurveyFormRequest;
import id.ezclouds.core.bifrost.core.converter.BizRequestConverter;
import id.ezclouds.core.bifrost.core.processor.BizProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiBizProcessor.java, v 0.1 2023‐12‐09 3:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class ApiBizProcessor implements BizProcessor {

    private static final String SOURCE_ID = "API";

    @Autowired
    private BizCommonConfigService bizCommonConfigService;

    @Autowired
    private BizAppSurveyService bizAppSurveyService;

    @Autowired
    private BizCandidateProfileService bizCandidateProfileService;

    @Autowired
    private BizNewsService bizNewsService;

    @Autowired
    private BizMemberProfileService bizMemberProfileService;

    @Autowired
    private BizMemberService bizMemberService;

    @Autowired
    private BizAuthService bizAuthService;

    @Autowired
    private BizAdminService bizAdminService;

    @Autowired
    private BizLocalAreaService bizLocalAreaService;

    @Autowired
    private BizSubOrganizationService bizSubOrganizationService;

    @Override
    public BizResult process(ApiEvent apiEvent, ApiRequest apiRequest, MultipartFile file) throws EzErrorException {

        switch (apiEvent) {
            case API_APP_SETTING:
                return bizCommonConfigService.getAppSetting();

            case API_SURVEY_FORM:
                return bizAppSurveyService.getSurveyForm(((SurveyFormRequest)apiRequest).getSurveyId());

            case API_CANDIDATE_PROFILE:
                return bizCandidateProfileService.getCandidateProfile();

            case API_NEWS:
                return bizNewsService.getActiveNews();

            case API_MEMBER_PROFILE:
                return bizMemberService.getMemberProfile();

            case API_MEMBER_LOGIN:
                BizRequestConverter<BizMemberLoginRequest> loginConverter = new BizRequestConverter<>(BizRequestConverter.MEMBER_LOGIN);
                return bizAuthService.memberLogin(loginConverter.convert(apiRequest));

            case API_SESSION_CHECK:
                return bizAuthService.memberSessionCheck();

            case API_MEMBER_LOGOUT:
                return bizAuthService.memberLogout();

            case API_MEMBER_UPDATE_PASSWORD:
                BizRequestConverter<BizMemberUpdatePasswordRequest> converter = new BizRequestConverter<>(BizRequestConverter.UPDATE_PASSWORD);
                return bizAuthService.memberUpdatePassword(converter.convert(apiRequest));

            case API_MEMBER_RESET_PASSWORD:
                BizRequestConverter<BizMemberResetPasswordRequest> resetConverter = new BizRequestConverter<>(BizRequestConverter.RESET_PASSWORD);
                return bizAuthService.memberResetPassword(resetConverter.convert(apiRequest));

            case API_MEMBER_VERIFY_COMMON_SESSION:
                BizRequestConverter<BizVerifyCommonSessionRequest> verifyConverter = new BizRequestConverter<>(BizRequestConverter.VERIFY_COMMON_SESSION);
                return bizAuthService.memberVerifyCommonSession(verifyConverter.convert(apiRequest));

            case API_MEMBER_REGISTER:
                BizRequestConverter<BizMemberRegisterRequest> registerConverter = new BizRequestConverter<>(BizRequestConverter.MEMBER_REGISTER);
                BizMemberRegisterRequest bizRequest = registerConverter.convert(apiRequest);
                bizRequest.getExtendInfo().put(AppConstant.ExtKey.SOURCE_ID, SOURCE_ID);
                return bizMemberService.registerMember(bizRequest);

            case API_SURVEY_SUBMIT:
                BizRequestConverter<BizSurveySubmitRequest> submitSurvey = new BizRequestConverter<>(BizRequestConverter.SURVEY_SUBMIT);
                return bizAppSurveyService.submitSurvey(submitSurvey.convert(apiRequest));

            case API_MEMBER_UPLOAD_MEDIA:
                BizRequestConverter<BizMemberUploadRequest> uploadConverter = new BizRequestConverter<>(BizRequestConverter.BIZ_COMMON_UPLOAD);
                BizMemberUploadRequest uploadRequest = uploadConverter.convert(apiRequest);
                uploadRequest.setMultipartFile(file);
                return bizMemberService.memberUploadMedia(uploadRequest);

            case API_GET_LOCAL_AREA:
                BizRequestConverter<BizLocalAreaRequest> localAreaConverter = new BizRequestConverter<>(BizRequestConverter.LOCAL_AREA);
                return bizLocalAreaService.getLocalArea(localAreaConverter.convert(apiRequest));

            case API_SUB_ORG_CREATE:
                BizRequestConverter<BizSubOrgCreateRequest> subOrgCreateConverter = new BizRequestConverter<>(BizRequestConverter.SUB_ORG_CREATE);
                return bizSubOrganizationService.create(subOrgCreateConverter.convert(apiRequest));




            // =========== ADMIN BIZ PROCESS =============
            case API_ADMIN_CREATE_WEB_SESSION:
                return bizAdminService.createWebSession();

            case API_ADMIN_GET_WEB_SESSION:
                return bizAdminService.getWebSession();

            case API_ADMIN_LOGOUT_WEB_SESSION:
                return bizAdminService.logoutWebSession(apiRequest.getExtendInfo().get("WEB_SESSION_ID"));

        }

        BizResult bizResult = new BizResult();
        bizResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
        bizResult.setErrorLocation(getClass().getName());
        bizResult.setErrorMessage("Undefined bizProcessor");
        return bizResult;
    }
}