/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.integration.request.WhatsappSendRequest;
import id.ezclouds.core.integration.result.IntegrationResult;
import id.ezclouds.core.integration.service.client.WatzapClientService;
import id.ezclouds.core.integration.service.client.request.WatzapSendRequest;
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
    private WatzapClientService watzapClientService;

    @Async
    public void sendWhatsappMessage(WhatsappSendRequest request) {
        IntegrationResult result = new IntegrationResult();

        CoreIntegrationServiceTemplate.execute(request, result, new CoreIntegrationServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest is null");
                AssertUtil.notBlank(request.getPhoneNumber(), EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest.phoneNumber is blank");
                AssertUtil.notBlank(request.getMessage(), EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest.message is blank");
            }

            @Override
            public void onProcess() throws Exception {
                WatzapSendRequest sendRequest = new WatzapSendRequest();
                sendRequest.setPhone_no(request.getPhoneNumber());
                sendRequest.setMessage(request.getMessage());
                watzapClientService.sendWatzap(sendRequest)
                        .subscribe(responseEntity -> {
                            System.out.println(responseEntity);
                        });
                result.setSuccess(true);
            }
        });
    }

    private void sendDummyWhatsapp() {
        watzapClientService
                .sendWatzap()
                .subscribe(responseEntity -> {
                    System.out.println("Status: " + responseEntity.getStatusCodeValue());
                    System.out.println("Location URI: " + responseEntity.getHeaders().getLocation());
                    System.out.println("Response: " + responseEntity.getBody());
                });
    }
}