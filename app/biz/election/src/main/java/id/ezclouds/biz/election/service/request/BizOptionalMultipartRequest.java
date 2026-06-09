/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.request;

import id.ezclouds.common.model.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizOptionalMultipartRequest.java, v 0.1 2024‐04‐09 6:57 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizOptionalMultipartRequest extends BizRequest {

    private boolean isMultipartFileRequired = true;

    public boolean isMultipartFileRequired() {
        return isMultipartFileRequired;
    }

    public void setMultipartFileOptional() {
        isMultipartFileRequired = false;
    }
}