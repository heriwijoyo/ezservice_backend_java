/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service;

import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.integration.request.WhatsappSendRequest;
import id.ezclouds.core.integration.result.IntegrationResult;
import id.ezclouds.core.integration.service.client.WatzapClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreIntegrationService.java, v 0.1 2024‐02‐05 1:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreIntegrationService {

    @Autowired
    private WatzapClientService watzapClientService;

    public IntegrationResult sendWhatsappMessage(WhatsappSendRequest request) {
        IntegrationResult result = new IntegrationResult();

        CoreIntegrationServiceTemplate.execute(request, result, new CoreIntegrationServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {

            }

            @Override
            public void onProcess() throws Exception {

            }
        });

        return result;
    }
}