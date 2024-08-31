/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.receiver;

import id.ezclouds.common.facade.broker.BrokerMessageReceiver;
import id.ezclouds.common.model.broker.BrokerMessageData;
import id.ezclouds.common.model.broker.MsgSubConstant;
import id.ezclouds.common.model.broker.authorization.MemberAppClientAuthData;
import id.ezclouds.common.model.result.MessageReceiveResult;
import id.ezclouds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppClientLoginReceiver.java, v 0.1 2024‐08‐31 1:08 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Qualifier(MsgSubConstant.MEMBER_APP_CLIENT_LOGIN)
public class BizAppClientLoginReceiver implements BrokerMessageReceiver {

    @Override
    public MessageReceiveResult process(String topic, String event, BrokerMessageData data) {
        MessageReceiveResult result = new MessageReceiveResult();

        if (data instanceof MemberAppClientAuthData) {
        }

        result.setSuccess(true);
        result.setProcessTime(DateUtil.getCurrentFormattedDate());
        return result;
    }
}