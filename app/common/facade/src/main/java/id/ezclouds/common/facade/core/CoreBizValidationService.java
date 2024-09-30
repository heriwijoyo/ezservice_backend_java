/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.core;

import id.ezclouds.common.model.core.BizValidationScene;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizValidationService.java, v 0.1 2024‐09‐30 11:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreBizValidationService {

    void validate(String orgId, BizValidationScene scene, Object request);
}