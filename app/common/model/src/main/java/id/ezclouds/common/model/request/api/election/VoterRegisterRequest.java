/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.api.election;

import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.request.api.ApiRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VoterRegisterRequest.java, v 0.1 2024‐09‐29 3:08 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class VoterRegisterRequest extends ApiRequest {

    private BizVoter bizVoter;

    public BizVoter getBizVoter() {
        return bizVoter;
    }

    public void setBizVoter(BizVoter bizVoter) {
        this.bizVoter = bizVoter;
    }
}