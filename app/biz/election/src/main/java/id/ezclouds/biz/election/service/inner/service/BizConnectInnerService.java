/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.inner.service;

import id.ezclouds.biz.election.constant.BizConstant;
import id.ezclouds.biz.election.converter.BizMessageTemplateConverter;
import id.ezclouds.biz.election.enums.BizConnectType;
import id.ezclouds.biz.election.service.app.AppConfigService;
import id.ezclouds.common.facade.integration.EzConnectService;
import id.ezclouds.common.model.integration.WhatsappSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizConnectInnerService.java, v 0.1 2024‐04‐03 2:30 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizConnectInnerService {

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private EzConnectService ezConnectService;

    public void memberSendPassword(String orgId, String phone, String password, String appName, String downloadUrl) {
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
            request.setOrgId(orgId);
            request.setPhoneNumber(phone);
            request.setMessage(whatsappMessage);
            ezConnectService.sendWhatsappMessage(request);
        }
    }

    public void sendMessage(BizConnectType connectType, String orgId, String target, String message) {
        switch (connectType) {
            case WHATSAPP:
                WhatsappSendRequest sendRequest = composeWhatsappSendRequest(orgId, target, message);
                ezConnectService.sendWhatsappMessage(sendRequest);
                break;
        }
    }

    private WhatsappSendRequest composeWhatsappSendRequest(String orgId, String phone, String message) {
        WhatsappSendRequest sendRequest = new WhatsappSendRequest();
        sendRequest.setOrgId(orgId);
        sendRequest.setPhoneNumber(phone);
        sendRequest.setMessage(message);
        return sendRequest;
    }
}