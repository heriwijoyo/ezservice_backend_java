/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.form.CoreBizForm;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.CoreBizFormDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizFormConverter.java, v 0.1 2024‐11‐18 7:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreBizFormConverter extends CommonDOModelConverter<CoreBizFormDO, CoreBizForm> {

    @Override
    protected CoreBizForm safeConvertQuery(CoreBizFormDO dataObject) {
        CoreBizForm coreBizForm = new CoreBizForm();
        coreBizForm.setFormId(dataObject.getFormId());
        coreBizForm.setOrgId(dataObject.getOrgId());
        coreBizForm.setOrgCode(dataObject.getOrgCode());
        coreBizForm.setTitle(dataObject.getTitle());
        coreBizForm.setHtmlContent(dataObject.getHtmlContent());
        return coreBizForm;
    }

    @Override
    protected CoreBizFormDO safeConvertStore(CoreBizForm model) {
        return null;
    }
}