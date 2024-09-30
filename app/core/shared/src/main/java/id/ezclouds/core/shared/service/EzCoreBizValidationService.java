/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreBizValidationService;
import id.ezclouds.common.facade.dal.core.CoreBizValidationDAO;
import id.ezclouds.common.model.biz.BizValidationRule;
import id.ezclouds.common.model.core.BizValidationScene;
import id.ezclouds.common.model.core.CoreBizValidation;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreBizValidationService.java, v 0.1 2024‐09‐30 11:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreBizValidationService implements CoreBizValidationService {

    @Autowired
    private CoreBizValidationDAO coreBizValidationDAO;

    @Override
    public void validate(String orgId, BizValidationScene scene, Object request) {
        CoreBizValidation bizValidation = coreBizValidationDAO
                .getBizValidation(orgId, scene.getCode());
        if (bizValidation == null) {
            return;
        }

        AssertUtil.notNull(request, EzErrorCode.BIZ_VALIDATION_FAILED, "validation object is null");

        for (BizValidationRule validationRule : bizValidation.getValidationRules()) {
            validateByRule(validationRule, request);
        }
    }

    private void validateByRule(BizValidationRule validationRule, Object request) {
        switch (validationRule) {
            case NOT_BLANK:
                String fieldValue = (String) getFieldValue(request, validationRule.getField());
                AssertUtil.notBlank(fieldValue, EzErrorCode.BIZ_VALIDATION_FAILED);
                break;
        }
    }

    private Object getFieldValue(Object request, String fieldName) {
        try {
            Field field = request.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(request);
        } catch (Exception e) {
            return null;
        }
    }
}