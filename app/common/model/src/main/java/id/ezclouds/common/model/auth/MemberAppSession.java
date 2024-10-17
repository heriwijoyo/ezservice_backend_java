/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.auth;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberAppSession.java, v 0.1 2024‐09‐30 10:45 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberAppSession {

    private String memberId;
    private List<AuthRole> authRoles;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public List<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(List<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }
}