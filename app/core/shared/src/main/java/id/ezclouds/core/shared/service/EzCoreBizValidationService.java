/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreBizValidationService;
import id.ezclouds.common.model.core.BizValidationScene;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreBizValidationService.java, v 0.1 2024‐09‐30 11:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreBizValidationService implements CoreBizValidationService {

    @Override
    public void validate(String orgId, BizValidationScene scene, Object request) {
        //TODO: add validation logic here
    }
}