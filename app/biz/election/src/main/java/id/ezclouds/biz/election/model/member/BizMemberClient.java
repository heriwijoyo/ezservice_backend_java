/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.model.member;

import id.ezclouds.biz.election.model.BizStatus;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberClient.java, v 0.1 2024‐01‐07 10:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberClient {

    private String clientId;
    private String loginType;
    private BizStatus status;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public BizStatus getStatus() {
        return status;
    }

    public void setStatus(BizStatus status) {
        this.status = status;
    }
}