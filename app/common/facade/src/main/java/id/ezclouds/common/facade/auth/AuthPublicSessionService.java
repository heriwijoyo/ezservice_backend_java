/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.auth;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthPublicSessionService.java, v 0.1 2024‐10‐10 1:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthPublicSessionService {

    String generateWebPublicSession(String orgId);
}