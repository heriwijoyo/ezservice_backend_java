/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.broker;

import id.ezclouds.common.facade.broker.BrokerMessageDataConvertService;
import id.ezclouds.common.facade.dal.broker.BrokerMessageDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.broker.BrokerMessage;
import id.ezclouds.common.model.broker.BrokerMessageData;
import id.ezclouds.core.dal.broker.converter.BrokerMessageConverter;
import id.ezclouds.core.dal.broker.dataobject.EzBrokerMessageDO;
import id.ezclouds.core.dal.broker.repo.EzBrokerMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBrokerMessageDAO.java, v 0.1 2024‐08‐31 10:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBrokerMessageDAO implements BrokerMessageDAO {

    @Autowired
    private EzBrokerMessageRepository ezBrokerMessageRepository;

    @Autowired
    private BrokerMessageDataConvertService brokerMessageDataConvertService;

    @EzDAOLogger
    @Override
    public String storeMessage(BrokerMessage message) {
        BrokerMessageConverter converter = new BrokerMessageConverter();
        EzBrokerMessageDO messageDO = converter.convertStore(message);
        messageDO.setPayload(brokerMessageDataConvertService.convertPayload(message.getPayload()));

        return ezBrokerMessageRepository
                .saveAndFlush(messageDO)
                .getMessageId();
    }

    @EzDAOLogger
    @Override
    public BrokerMessage getMessage(String messageId) {
        EzBrokerMessageDO messageDO = ezBrokerMessageRepository
                .findById(messageId)
                .orElse(null);
        BrokerMessage message = new BrokerMessageConverter().convertQuery(messageDO);
        BrokerMessageData messageData = brokerMessageDataConvertService
                .parsePayload(message.getTopic(), message.getEvent(), messageDO.getPayload());
        message.setPayload(messageData);
        return message;
    }
}