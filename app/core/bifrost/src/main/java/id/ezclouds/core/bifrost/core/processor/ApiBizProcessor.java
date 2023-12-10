/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.arahindonesia.service.AppSettingService;
import id.ezclouds.biz.arahindonesia.service.api.CandidateProfileService;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.core.BaseRequest;
import id.ezclouds.core.shared.context.EzAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiBizProcessor.java, v 0.1 2023‐12‐09 3:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class ApiBizProcessor implements BizProcessor {

    @Autowired
    private AppSettingService appSettingService;

    @Autowired
    private CandidateProfileService candidateProfileService;

    @Override
    public Object process(EzAppEvent appEvent, BaseRequest request) {
        ApiEvent apiEvent = (ApiEvent) appEvent;

        switch (apiEvent) {
            case API_APP_SETTING:
                return appSettingService.getAppSetting();

            case CANDIDATE_PROFILE:
                return candidateProfileService.getCandidateProfile();
        }
        return null;
    }
}