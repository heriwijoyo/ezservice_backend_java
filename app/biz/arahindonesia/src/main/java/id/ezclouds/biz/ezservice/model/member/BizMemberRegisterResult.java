/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.member;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRegisterResult.java, v 0.1 2024‐02‐18 11:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberRegisterResult {

    private String memberId;
    private String message;
    private Map<String, String> extendInfo = new HashMap<>();

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getExtendInfo() {
        return extendInfo;
    }
}