/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberUpdateAvatarRequest.java, v 0.1 2024‐02‐07 3:30 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberUpdateAvatarRequest extends ApiRequest {

    private String section;
    private String nickname;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}