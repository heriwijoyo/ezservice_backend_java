/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.app;

import id.ezclouds.common.model.app.EzApplication;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAppService.java, v 0.1 2024‐08‐12 9:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface EzAppService {
    EzApplication getActiveApp(String orgId);
}