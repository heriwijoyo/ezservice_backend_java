/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.constant.BizConstant;
import id.ezclouds.biz.ezservice.converter.BizMessageTemplateConverter;
import id.ezclouds.biz.ezservice.service.app.AppConfigService;
import id.ezclouds.core.integration.request.WhatsappSendRequest;
import id.ezclouds.core.integration.service.EzConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizConnectService.java, v 0.1 2024‐04‐03 2:30 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizConnectService {

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private EzConnectService ezConnectService;

    public void memberSendPassword(String phone, String password, String appName, String downloadUrl) {
        String messageTemplate = appConfigService
                .getMessageTemplate(BizConstant.TemplateKey.WA_MEMBER_CREATE_PASSWORD);
        Map<String, String> values = new HashMap<>();
        values.put(BizConstant.TemplateKey.APP_NAME, appName);
        values.put(BizConstant.TemplateKey.PHONE, phone);
        values.put(BizConstant.TemplateKey.PASSWORD, password);
        values.put(BizConstant.TemplateKey.APP_DOWNLOAD_URL, downloadUrl);
        String whatsappMessage = BizMessageTemplateConverter.getMessage(messageTemplate, values);

        if (whatsappMessage != null) {
            WhatsappSendRequest request = new WhatsappSendRequest();
            request.setPhoneNumber(phone);
            request.setMessage(whatsappMessage);
            ezConnectService.sendWhatsappMessage(request);
        }
    }
}