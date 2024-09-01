/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service;

import id.ezclouds.common.facade.integration.EzConnectService;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.integration.dataservice.ConnectDbLoggerService;
import id.ezclouds.common.model.integration.WhatsappLog;
import id.ezclouds.common.model.integration.WhatsappLogRequest;
import id.ezclouds.common.model.integration.WhatsappResendRequest;
import id.ezclouds.common.model.integration.WhatsappSendRequest;
import id.ezclouds.common.model.integration.EzConnectResult;
import id.ezclouds.core.integration.service.client.service.WatzapClientService;
import id.ezclouds.core.integration.service.client.request.WatzapSendRequest;
import id.ezclouds.common.model.result.BizPageInfo;
import id.ezclouds.core.shared.service.LegacyCoreConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreConnectService.java, v 0.1 2024‐02‐05 1:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreConnectService implements EzConnectService {

    @Autowired
    private LegacyCoreConfigService legacyCoreConfigService;

    @Autowired
    private WatzapClientService watzapClientService;

    @Autowired
    private ConnectDbLoggerService connectDbLoggerService;

    @Override
    public EzConnectResult sendWhatsappMessage(WhatsappSendRequest request) {
        final EzConnectResult result = new EzConnectResult();

        ConnectServiceTemplate.execute(request, result, new ConnectServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest is null");
                AssertUtil.notBlank(request.getOrgId(), EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest.orgId is blank");
                AssertUtil.notBlank(request.getPhoneNumber(), EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest.phoneNumber is blank");
                AssertUtil.notBlank(request.getMessage(), EzErrorCode.ILLEGAL_PARAM, "WhatsappSendRequest.message is blank");
            }

            @Override
            public void onProcess() throws Exception {
                String orgId = request.getOrgId();
                if (legacyCoreConfigService.isWatzapSendEnable(orgId)) {
                    WatzapSendRequest sendRequest = new WatzapSendRequest();
                    sendRequest.setApi_key(legacyCoreConfigService.getWatzapApiKey(orgId));
                    sendRequest.setNumber_key(legacyCoreConfigService.getWatzapNumberKey(orgId));
                    sendRequest.setApiUri(legacyCoreConfigService.getWatzapApiUri());
                    sendRequest.setPhone_no(request.getPhoneNumber());
                    sendRequest.setMessage(request.getMessage());

                    String logId = connectDbLoggerService.logWatzapMessage(request, sendRequest);

                    watzapClientService
                            .sendWatzap(sendRequest)
                            .subscribe(response -> {
                                result.setData(response);
                                ConnectServiceLogger.logResult(result);
                                connectDbLoggerService.updateWatzapLog(logId, result.getTraceId(), response);
                            });
                }
                result.setSuccess(true);
            }
        });

        return result;
    }

    @Override
    public EzConnectResult getWhatsappLog(WhatsappLogRequest request) {
        final EzConnectResult result = new EzConnectResult();
        ConnectServiceTemplate.execute(request, result, new ConnectServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getPageRequest(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onProcess() throws Exception {
                BizPageInfo<WhatsappLog> pageInfo = connectDbLoggerService
                        .getWhatsappLogs(request);
                result.setSuccess(true);
                result.setData(pageInfo);
            }
        });
        return result;
    }

    @Override
    public EzConnectResult resendWhatsapp(WhatsappResendRequest request) {
        final EzConnectResult result = new EzConnectResult();
        ConnectServiceTemplate.execute(null, result, new ConnectServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getMessageId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onProcess() throws Exception {
                WhatsappLog whatsappLog = connectDbLoggerService
                        .getWhatsappLog(request.getOrgId(), request.getMessageId());
                AssertUtil.notNull(whatsappLog, EzErrorCode.DATA_NOT_FOUND);

                WhatsappSendRequest sendRequest = new WhatsappSendRequest();
                sendRequest.setOrgId(whatsappLog.getOrgId());
                sendRequest.setPhoneNumber(whatsappLog.getPhone());
                sendRequest.setMessage(whatsappLog.getMessage());

                EzConnectResult ezConnectResult = sendWhatsappMessage(sendRequest);
                if (ezConnectResult.isSuccess()) {
                    connectDbLoggerService.updateWhatsappResend(request.getOrgId(), request.getMessageId());
                }

                result.setSuccess(ezConnectResult.isSuccess());
                result.setData(ezConnectResult.getData());
            }
        });
        return result;
    }
}