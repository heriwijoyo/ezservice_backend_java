/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service.inner;

import id.ezclouds.common.facade.dal.broker.BrokerMessageDAO;
import id.ezclouds.common.model.broker.BrokerMessage;
import id.ezclouds.common.model.broker.BrokerMessageData;
import id.ezclouds.common.model.broker.member.MemberRegisterMessageData;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.broker.model.BrokerTopicEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
            case CORE_MEMBER_REGISTER:
                AssertUtil.isTrue(messageData instanceof MemberRegisterMessageData, EzErrorCode.ILLEGAL_PARAM);
                break;
        }
    }

    @Transactional
    public String storeMessage(BrokerMessage message) {
        return brokerMessageDAO.storeMessage(message);
    }

    public void dispatchMessage(String messageId) {

    }
}