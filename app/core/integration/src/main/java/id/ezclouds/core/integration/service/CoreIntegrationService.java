/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.integration.request.WhatsappSendRequest;
import id.ezclouds.core.integration.service.client.WatzapClientService;
import id.ezclouds.core.integration.service.client.request.WatzapSendRequest;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.service.CoreConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreIntegrationService.java, v 0.1 2024‐02‐05 1:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreIntegrationService {

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private WatzapClientService watzapClientService;

    @Async
    public void sendWhatsappMessage(WhatsappSendRequest request) {
        CoreIntegrationServiceTemplate.executeAsync(request, new CoreIntegrationServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest is null");
                AssertUtil.notBlank(request.getPhoneNumber(), EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest.phoneNumber is blank");
                AssertUtil.notBlank(request.getMessage(), EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest.message is blank");
            }

            @Override
            public void onProcess() throws Exception {
                if (isWatzapSendEnable()) {
                    WatzapSendRequest sendRequest = new WatzapSendRequest();
                    sendRequest.setPhone_no(request.getPhoneNumber());
                    sendRequest.setMessage(request.getMessage());
                    watzapClientService.sendWatzap(sendRequest)
                            .subscribe(responseEntity -> {
                                System.out.println(responseEntity);
                            });
                }
            }
        });
    }

    private boolean isWatzapSendEnable() {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        String configValue = coreConfigService.getConfigValue("WATZAP_SEND_ENABLE", orgId);
        return Boolean.parseBoolean(configValue);
    }
}