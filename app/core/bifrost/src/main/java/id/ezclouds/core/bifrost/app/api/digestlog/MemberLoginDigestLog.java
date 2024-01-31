/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberLoginDigestLog.java, v 0.1 2024‐01‐29 4:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberLoginDigestLog extends BaseDigestLog<BizMemberSession> {

    public MemberLoginDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigest(ApiRequest request, ApiResult<BizMemberSession> result) {
    }
}