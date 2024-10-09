/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.auth;

import id.ezclouds.common.model.auth.WebPublicSession;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthWebPublicSessionDAO.java, v 0.1 2024‐10‐10 12:36 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthWebPublicSessionDAO {

    void store(WebPublicSession webPublicSession);
}