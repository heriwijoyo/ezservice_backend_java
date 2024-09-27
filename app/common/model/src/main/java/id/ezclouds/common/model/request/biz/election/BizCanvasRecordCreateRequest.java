/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.biz.election;

import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCanvasRecordCreateRequest.java, v 0.1 2024‐09‐23 11:04 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizCanvasRecordCreateRequest extends BizRequest {

    private String orgId;
    private String referrerMemberId;
    private final BizVoter bizVoter = new BizVoter();

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getReferrerMemberId() {
        return referrerMemberId;
    }

    public void setReferrerMemberId(String referrerMemberId) {
        this.referrerMemberId = referrerMemberId;
    }

    public BizVoter getBizVoter() {
        return bizVoter;
    }
}