/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.arahindonesia.model.AppSetting;
import id.ezclouds.biz.arahindonesia.service.AppClientService;
import id.ezclouds.biz.arahindonesia.service.CacheService;
import id.ezclouds.biz.arahindonesia.service.OrganizationService;
import id.ezclouds.common.dal.model.AppClient;
import id.ezclouds.common.dal.model.Organization;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiBaseRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.core.ControllerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiController.java, v 0.1 2023‐12‐03 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AppClientService appClientService;

    @Autowired
    private CacheService cacheService;

    @PostMapping(value = "/appSetting.json", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ApiResult<AppSetting> getAppSetting(@RequestBody ApiBaseRequest request) {
        ApiResult<AppSetting> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);

        ControllerTemplate
                .withEvent(ApiEvent.API_APP_SETTING)
                .withRequest(request)
                .withHandler(new ControllerTemplate.Handler() {
                    @Override
                    public void onResult(Object result) {
                        apiResult.setData((AppSetting) result);
                    }

                    @Override
                    public void onError(ErrorResult errorResult) {
                        setErrorResult(apiResult, errorResult);
                    }
                })
                .process();

        return apiResult;
    }

    private void setErrorResult(ApiResult apiResult, ErrorResult errorResult) {
        apiResult.setSuccess(false);
        apiResult.setErrorResult(errorResult);
    }






    @GetMapping("/test")
    public String test() {
        return "API test";
    }

    @GetMapping("/organizations")
    public List<Organization> getOrganization() {
        return organizationService.getOrganizations();
    }

    @GetMapping("/app_clients")
    public List<AppClient> getAppClients() {
        return appClientService.getAppClients();
    }

    @GetMapping("refresh_cache")
    public List<String> refreshAllCache() {
        return cacheService.refreshAllCache();
    }
}