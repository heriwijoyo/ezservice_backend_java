/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.request.api.ApiRequest;
import id.ezclouds.common.model.result.api.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VoterRegisterDigestLog.java, v 0.1 2024‐09‐29 12:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class VoterRegisterDigestLog extends BaseDigestLog<BizVoter> {

    public VoterRegisterDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigest(ApiRequest request, ApiResult<BizVoter> result) {
        //TODO: add compose digest log
    }
}