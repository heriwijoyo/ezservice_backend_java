/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.biz.data.BizSmartTableDataSource;
import id.ezclouds.common.model.biz.table.BizSmartTableQueryScenario;
import id.ezclouds.common.model.constant.BizTableQueryConstant;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BaseDataSource.java, v 0.1 2024‐09‐15 5:03 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class BaseDataSource implements BizSmartTableDataSource {

    protected String getTitle(Map<String, String> conditions) {
        return getQueryScenario(conditions).getTitle();
    }

    protected BizSmartTableQueryScenario getQueryScenario(Map<String, String> condition) {
        String queryScenario = condition.get(BizTableQueryConstant.SCENARIO);
        return BizSmartTableQueryScenario.getByCode(queryScenario);
    }
}