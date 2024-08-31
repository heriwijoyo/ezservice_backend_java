/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service;

import id.ezclouds.common.facade.broker.BrokerMessageSendService;
import id.ezclouds.common.model.broker.BrokerMessage;
import id.ezclouds.common.model.result.MessageSendResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.broker.model.BrokerTopicEvent;
import id.ezclouds.core.broker.service.inner.MessageSendInnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBrokerMessageSendService.java, v 0.1 2024‐08‐31 8:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBrokerMessageSendService implements BrokerMessageSendService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.BROKER);

    @Autowired
    private MessageSendInnerService messageSendInnerService;

    @Override
    public MessageSendResult send(BrokerMessage message) {
        MessageSendResult result = new MessageSendResult();
        result.setSuccess(false);

        String orgId = null;
        String source = null;
        String topic = null;
        String event = null;
        String messageId = null;

        try {
            // request validation
            AssertUtil.notNull(message, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(message.getOrgId(), EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(message.getSource(), EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notNull(message.getPayload(), EzErrorCode.ILLEGAL_PARAM);

            BrokerTopicEvent topicEvent = BrokerTopicEvent
                    .getByTopicAndEvent(message.getTopic(), message.getEvent());
            AssertUtil.isNotTrue(topicEvent == BrokerTopicEvent.UNKNOWN, EzErrorCode.ILLEGAL_PARAM);

            // logging info
            orgId = message.getOrgId();
            source = message.getSource();
            topic = message.getTopic();
            event = message.getEvent();

            // biz validation
            messageSendInnerService.validateMessageData(topicEvent, message.getPayload());

            // process request
            messageId = messageSendInnerService.storeMessage(message);
            messageSendInnerService.dispatchMessage(messageId);

            result.setSuccess(true);
            result.setMessageId(messageId);
        } catch (Exception exception) {

            if (exception instanceof EzErrorException) {
                result.setErrorCode(((EzErrorException)exception).getEzErrorCode());
            } else {
                result.setErrorCode(EzErrorCode.SYSTEM_ERROR);
            }
            result.setSuccess(false);

        } finally {
            if (orgId == null) {
                orgId = "ORG_ID_NULL";
            }
            if (source == null) {
                source = "SOURCE_NULL";
            }
            if (topic == null) {
                topic = "TOPIC_NULL";
            }
            if (event == null) {
                event = "EVENT_NULL";
            }
            if (messageId == null) {
                messageId = "MESSAGE_ID_NULL";
            }

            LOGGER.info(
                    orgId +","+ source +","+ topic +","+ event +","+ messageId
            );
        }

        return result;
    }
}