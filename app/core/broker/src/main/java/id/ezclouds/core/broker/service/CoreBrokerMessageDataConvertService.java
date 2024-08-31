/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service;

import id.ezclouds.common.facade.broker.BrokerMessageDataConvertService;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.broker.BrokerMessageData;
import id.ezclouds.common.model.broker.authorization.MemberAppClientAuthData;
import id.ezclouds.common.model.broker.member.MemberRegisterData;
import id.ezclouds.core.broker.model.BrokerTopicEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBrokerMessageDataConvertService.java, v 0.1 2024‐08‐31 12:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBrokerMessageDataConvertService implements BrokerMessageDataConvertService {

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Override
    public String convertPayload(BrokerMessageData messageData) {
        return bizObjectMapperService.toJson(messageData);
    }

    @Override
    public BrokerMessageData parsePayload(String topic, String event, String payload) {
        BrokerTopicEvent topicEvent = BrokerTopicEvent.getByTopicAndEvent(topic, event);
        switch (topicEvent) {
            case MEMBER_CLIENT_APP_LOGIN:
                return bizObjectMapperService.parseJson(payload, MemberAppClientAuthData.class);

            case CORE_MEMBER_REGISTER:
                return bizObjectMapperService.parseJson(payload, MemberRegisterData.class);
        }
        return null;
    }
}