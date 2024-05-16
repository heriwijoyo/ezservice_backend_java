/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.dataservice;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.integration.dataservice.dataobject.WatzapLogDO;
import id.ezclouds.core.integration.dataservice.repo.EzCoreConnectLogsWatzapRepository;
import id.ezclouds.core.integration.request.WhatsappSendRequest;
import id.ezclouds.core.integration.result.EzConnectResult;
import id.ezclouds.core.integration.service.client.request.WatzapSendRequest;
import id.ezclouds.core.integration.service.client.response.WatzapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ConnectDbLoggerService.java, v 0.1 2024‐05‐16 3:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class ConnectDbLoggerService {

    @Autowired
    private EzCoreConnectLogsWatzapRepository ezCoreConnectLogsWatzapRepository;

    @Transactional
    public String logWatzapMessage(WhatsappSendRequest waRequest, WatzapSendRequest wzRequest) {
        String orgId = waRequest.getOrgId();
        String phone = waRequest.getPhoneNumber();
        String currentTime = DateUtil.getCurrentFormattedDate();

        WatzapLogDO watzapLogDO = new WatzapLogDO();
        watzapLogDO.setId(HashUtil.createHash(orgId, phone, currentTime));
        watzapLogDO.setOrgId(orgId);
        watzapLogDO.setTarget(phone);
        watzapLogDO.setMessage(waRequest.getMessage());
        watzapLogDO.setCreatedTime(currentTime);
        watzapLogDO.setStatus(ConnectStatus.SENDING.getCode());
        watzapLogDO.setCreadentials(encodeWzCredential(wzRequest));
        ezCoreConnectLogsWatzapRepository.saveAndFlush(watzapLogDO);

        return watzapLogDO.getId();
    }

    @Transactional
    public void updateWatzapLog(String logId, String traceId, ResponseEntity<WatzapResponse> responseEntity) {
        WatzapLogDO watzapLogDO = ezCoreConnectLogsWatzapRepository
                .findById(logId)
                .orElse(null);

        if (watzapLogDO != null) {
            String response = traceId + " --- ";
            if (responseEntity != null) {
                if (responseEntity.getBody() != null) {
                    response += responseEntity.getBody().toString();
                    String status = "200".equals(responseEntity.getBody().getStatus()) ? ConnectStatus.SUCCESS.getCode() : ConnectStatus.FAILED.getCode();
                    watzapLogDO.setStatus(status);
                }
            }
            watzapLogDO.setResponse(response);
            watzapLogDO.setResponseTime(DateUtil.getCurrentFormattedDate());
            ezCoreConnectLogsWatzapRepository.saveAndFlush(watzapLogDO);
        }
    }


    private String encodeWzCredential(WatzapSendRequest request) {
        if (request == null) { return null; }
        String numberKey = StringUtil.defaultIfBlank(request.getNumber_key(), "");
        String apiKey = StringUtil.defaultIfBlank(request.getApi_key(), "");
        return "API=" + apiKey + ";NUMBER=" + numberKey;
    }
}