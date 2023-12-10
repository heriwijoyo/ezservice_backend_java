/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.arahindonesia.model.AppSetting;
import id.ezclouds.biz.arahindonesia.model.news.SimpleNews;
import id.ezclouds.biz.arahindonesia.model.profile.CandidateProfile;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiBaseRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.app.api.result.ListResult;
import id.ezclouds.core.bifrost.core.ControllerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiController.java, v 0.1 2023‐12‐03 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@RestController
@RequestMapping(value = "/api", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
public class ApiController {

    @Autowired
    private ControllerTemplate controllerTemplate;

    @PostMapping(value = "/appSetting.json")
    public ApiResult<AppSetting> getAppSetting(@RequestBody ApiBaseRequest request, HttpServletResponse httpServletResponse) {
        ApiResult<AppSetting> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);

        controllerTemplate.setAppEvent(ApiEvent.API_APP_SETTING);
        controllerTemplate.setBaseRequest(request);
        controllerTemplate.process(new ControllerTemplate.Handler() {
            @Override
            public void onResult(Object result) {
                AppSetting appSetting = null;
                if (result instanceof AppSetting) {
                    appSetting = (AppSetting) result;
                }
                apiResult.setData(appSetting);
            }

            @Override
            public void onError(ErrorResult errorResult) {
                setErrorResult(apiResult, errorResult, httpServletResponse);
            }
        });

        return apiResult;
    }

    @PostMapping(value = "/candidateProfile.json")
    public ApiResult<CandidateProfile> getCandidateProfile(@RequestBody ApiBaseRequest request, HttpServletResponse httpServletResponse) {
        ApiResult<CandidateProfile> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);

        controllerTemplate.setAppEvent(ApiEvent.CANDIDATE_PROFILE);
        controllerTemplate.setBaseRequest(request);
        controllerTemplate.process(new ControllerTemplate.Handler() {
            @Override
            public void onResult(Object result) {
                CandidateProfile profile = null;
                if (result instanceof CandidateProfile) {
                    profile = (CandidateProfile) result;
                }
                apiResult.setData(profile);
            }

            @Override
            public void onError(ErrorResult errorResult) {
                setErrorResult(apiResult, errorResult, httpServletResponse);
            }
        });

        return apiResult;
    }

    @PostMapping(value = "/news.json")
    public ApiResult<List<SimpleNews>> getNews(@RequestBody ApiBaseRequest request, HttpServletResponse httpServletResponse) {
        ApiResult<List<SimpleNews>> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);

        controllerTemplate.setAppEvent(ApiEvent.NEWS);
        controllerTemplate.setBaseRequest(request);
        controllerTemplate.process(new ControllerTemplate.Handler() {
            @Override
            public void onResult(Object result) {
                if (result instanceof ListResult) {
                    ListResult<SimpleNews> listResult = (ListResult) result;
                    apiResult.setData(listResult.getItems());
                }
            }

            @Override
            public void onError(ErrorResult errorResult) {
                setErrorResult(apiResult, errorResult, httpServletResponse);
            }
        });

        return apiResult;
    }

    private void setErrorResult(ApiResult apiResult, ErrorResult errorResult, HttpServletResponse httpServletResponse) {
        if (EzErrorCode.UNAUTHORIZED.getCode().equals(errorResult.getErrorCode())) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        if (EzErrorCode.SESSION_EXPIRED.getCode().equals(errorResult.getErrorCode())) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        apiResult.setSuccess(false);
        apiResult.setErrorResult(errorResult);
    }
}