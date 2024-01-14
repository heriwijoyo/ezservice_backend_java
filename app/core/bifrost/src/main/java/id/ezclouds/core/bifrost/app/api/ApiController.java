/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.arahindonesia.model.AppSetting;
import id.ezclouds.biz.arahindonesia.model.login.MemberLoginResult;
import id.ezclouds.biz.arahindonesia.model.member.BizMemberInfo;
import id.ezclouds.biz.arahindonesia.model.news.SimpleNews;
import id.ezclouds.biz.arahindonesia.model.profile.CandidateProfile;
import id.ezclouds.biz.arahindonesia.model.profile.MemberProfile;
import id.ezclouds.biz.arahindonesia.service.core.CacheService;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiBaseRequest;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberLoginRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberRegisterRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiController.java, v 0.1 2023‐12‐03 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
@RequestMapping(value = "/api", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
public class ApiController extends AppController {

    @PostMapping(value = "/appSetting.json")
    public ApiResult<AppSetting> getAppSetting(@RequestBody ApiBaseRequest request, HttpServletResponse httpServletResponse) {
        ApiResult<AppSetting> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);
        return apiResult;
    }

    @PostMapping(value = "/candidateProfile.json")
    public ApiResult<CandidateProfile> getCandidateProfile(@RequestBody ApiBaseRequest request, HttpServletResponse httpServletResponse) {
        ApiResult<CandidateProfile> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);
        return apiResult;
    }

    @PostMapping(value = "/news.json")
    public ApiResult<List<SimpleNews>> getNews(@RequestBody ApiBaseRequest request, HttpServletResponse httpServletResponse) {
        ApiResult<List<SimpleNews>> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);
        return apiResult;
    }

    @PostMapping(value = "/memberProfile.json")
    public ApiResult<MemberProfile> getMemberProfile(@RequestBody ApiBaseRequest request, HttpServletResponse httpServletResponse) {
        ApiResult<MemberProfile> apiResult = new ApiResult<>();
        return apiResult;
    }

    @PostMapping(value = "/memberLogin.json")
    public ApiResult<MemberLoginResult> getMemberLogin(@RequestBody MemberLoginRequest request, HttpServletResponse httpServletResponse) {
        ApiResult<MemberLoginResult> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);
        return apiResult;
    }

    @PostMapping(value = "/memberRegister.json")
    public ApiResult<BizMemberInfo> registerMember(@RequestBody MemberRegisterRequest request, HttpServletResponse response) {

        return null;
    }

    @PostMapping(value = "/seqGenerate.json")
    public ApiResult<String> seqGenerate(@RequestBody ApiRequest request, HttpServletResponse response) {
        return null;
    }

    @PostMapping(value = "/sample.json")
    private ApiResult<String> getSample(@RequestBody ApiRequest request, HttpServletResponse response) {
        return executeInTemplate(ApiEvent.SAMPLE_EVENT, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return null;
            }
        });
    }


    @Autowired
    private CacheService cacheService;

    @GetMapping("/reloadCache")
    public List<String> reloadCache() {
        return cacheService.refreshAllCache();
    }
}