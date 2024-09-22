/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.model.member;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberInfo.java, v 0.1 2024‐01‐07 1:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberInfo {

    private BizMember bizMember;
    private BizMemberClient bizMemberClient;

    public BizMember getBizMember() {
        return bizMember;
    }

    public void setBizMember(BizMember bizMember) {
        this.bizMember = bizMember;
    }

    public BizMemberClient getBizMemberClient() {
        return bizMemberClient;
    }

    public void setBizMemberClient(BizMemberClient bizMemberClient) {
        this.bizMemberClient = bizMemberClient;
    }
}