/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.broker.authorization;

import id.ezclouds.common.model.broker.BrokerMessageData;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberAppClientAuthData.java, v 0.1 2024‐08‐31 12:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberAppClientAuthData extends BrokerMessageData {

    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}