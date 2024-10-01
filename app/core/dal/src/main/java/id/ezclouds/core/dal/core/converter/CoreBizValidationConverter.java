/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.converter;

import id.ezclouds.common.model.biz.BizValidationRule;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.BizValidationScene;
import id.ezclouds.common.model.core.CoreBizValidation;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.dal.core.dataobject.CoreBizValidationDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizValidationConverter.java, v 0.1 2024‐10‐01 2:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreBizValidationConverter extends CommonDOModelConverter<CoreBizValidationDO, CoreBizValidation> {

    @Override
    protected CoreBizValidation safeConvertQuery(CoreBizValidationDO dataObject) {
        CoreBizValidation bizValidation = new CoreBizValidation();
        bizValidation.setBizValidationId(dataObject.getBizValidationId());
        bizValidation.setOrgId(dataObject.getOrgId());
        bizValidation.setValidationScene(BizValidationScene.getByCode(dataObject.getValidationScene()));
        bizValidation.setValidationRules(parseRules(dataObject.getValidationRules()));
        bizValidation.setStatus(dataObject.getStatus());
        return bizValidation;
    }

    @Override
    protected CoreBizValidationDO safeConvertStore(CoreBizValidation model) {
        return null;
    }

    private List<BizValidationRule> parseRules(String rules) {
        List<BizValidationRule> bizRules = new ArrayList<>();
        if (StringUtil.isBlank(rules)) {
            return bizRules;
        }

        for (String ruleStr : rules.split(",")) {
            String[] ruleComponents = ruleStr.split("-");
            if (ruleComponents.length > 1) {
                BizValidationRule validationRule = BizValidationRule.getByCode(ruleComponents[1]);
                validationRule.setField(ruleComponents[0]);

                if (ruleComponents.length > 2) {
                    validationRule.setParam(ruleComponents[2]);
                }

                bizRules.add(validationRule);
            }
        }
        return bizRules;
    }
}