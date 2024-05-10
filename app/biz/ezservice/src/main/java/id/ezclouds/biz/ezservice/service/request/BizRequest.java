/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizRequest.java, v 0.1 2023‐12‐31 2:32 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizRequest {

    private String requestId;
    private Map<String, String> extendInfo = new HashMap<>();

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Map<String, String> getExtendInfo() {
        return extendInfo;
    }
}