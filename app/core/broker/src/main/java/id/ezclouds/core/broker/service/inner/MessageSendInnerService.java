/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service.inner;

import id.ezclouds.common.facade.dal.broker.BrokerMessageDAO;
import id.ezclouds.common.model.broker.BrokerMessage;
import id.ezclouds.common.model.broker.BrokerMessageData;
import id.ezclouds.common.model.broker.authorization.MemberAppClientAuthData;
import id.ezclouds.common.model.broker.member.MemberRegisterData;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.broker.model.BrokerTopicEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MessageSendInnerService.java, v 0.1 2024‐08‐31 10:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class MessageSendInnerService {

    @Autowired
    private BrokerMessageDAO brokerMessageDAO;

    @Autowired
    private ProcessDispatchService processDispatchService;

    public void validateMessageData(BrokerTopicEvent topicEvent, BrokerMessageData messageData) {
        switch (topicEvent) {
            case MEMBER_CLIENT_APP_LOGIN:
                AssertUtil.isTrue(messageData instanceof MemberAppClientAuthData, EzErrorCode.ILLEGAL_PARAM);
                break;

            case CORE_MEMBER_REGISTER:
                AssertUtil.isTrue(messageData instanceof MemberRegisterData, EzErrorCode.ILLEGAL_PARAM);
                break;
        }
    }

    @Transactional
    public String storeMessage(BrokerMessage message) {
        String currentTime = DateUtil.getCurrentFormattedDate();
        message.setMessageId(HashUtil.createHash(message.getOrgId(), message.getTopic(), message.getEvent(), currentTime));
        message.setCreatedTime(currentTime);
        return brokerMessageDAO.storeMessage(message);
    }

    public void dispatchMessage(BrokerTopicEvent topicEvent, String messageId) {
        List<String> subscribers = StaticBrokerSubscriber.getSubscribers(topicEvent);
        if (subscribers.size() > 0) {
            BrokerMessage message = brokerMessageDAO.getMessage(messageId);

            for (String subscriberId : subscribers) {
                processDispatchService.process(subscriberId, message);
            }
        }
    }
}