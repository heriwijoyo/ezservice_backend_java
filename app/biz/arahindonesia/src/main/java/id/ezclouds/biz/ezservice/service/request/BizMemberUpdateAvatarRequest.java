/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberUpdateAvatarRequest.java, v 0.1 2024‐02‐07 3:36 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberUpdateAvatarRequest extends BizMultipartRequest {

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    protected List<String> getSupportedContentType() {
        return imageTypes;
    }
}