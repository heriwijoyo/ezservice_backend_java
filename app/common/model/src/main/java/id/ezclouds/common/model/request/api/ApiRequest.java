/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.api;

import id.ezclouds.common.model.auth.AuthAppClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiRequest.java, v 0.1 2024‐09‐29 12:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ApiRequest {

    private AuthAppClient appClient;
    private AppSession appSession;
    private Map<String, String> extendInfo = new HashMap<>();


}