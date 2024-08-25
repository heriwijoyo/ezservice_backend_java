/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.EzBizCommonTableDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCommonTableConverter.java, v 0.1 2024‐08‐16 5:26 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizCommonTableConverter extends CommonDOModelConverter<EzBizCommonTableDO, BizCommonTable> {

    @Override
    protected BizCommonTable safeConvertQuery(EzBizCommonTableDO dataObject) {
        BizCommonTable bizCommonTable = new BizCommonTable();
        bizCommonTable.setTableId(dataObject.getTableId());
        bizCommonTable.setOrgId(dataObject.getOrgId());
        bizCommonTable.setCode(dataObject.getCode());
        bizCommonTable.setTableId(dataObject.getTableId());
        bizCommonTable.setTitle(dataObject.getTitle());
        bizCommonTable.setColumns(dataObject.getColumns());
        bizCommonTable.setConfig(dataObject.getConfig());
        bizCommonTable.setCreatedTime(dataObject.getCreatedTime());
        bizCommonTable.setStatus(dataObject.getStatus());
        return bizCommonTable;
    }

    @Override
    protected EzBizCommonTableDO safeConvertStore(BizCommonTable model) {
        EzBizCommonTableDO tableDO = new EzBizCommonTableDO();
        tableDO.setTableId(model.getTableId());
        tableDO.setOrgId(model.getOrgId());
        tableDO.setCode(model.getCode());
        tableDO.setPageId(model.getPageId());
        tableDO.setTitle(model.getTitle());
        tableDO.setColumns(model.getColumns());
        tableDO.setConfig(model.getConfig());
        tableDO.setCreatedTime(model.getCreatedTime());
        tableDO.setStatus(model.getStatus());
        return tableDO;
    }
}