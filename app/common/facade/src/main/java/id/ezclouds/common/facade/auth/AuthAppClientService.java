/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.auth;

import id.ezclouds.common.model.auth.AuthAppClient;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthAppClientService.java, v 0.1 2024‐09‐29 1:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthAppClientService {

    List<AuthAppClient> getAllActiveAppClients();

    boolean authorizeAppClient(AuthAppClient authAppClient);
}