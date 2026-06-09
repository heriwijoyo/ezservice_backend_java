/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.survey.BizSurveyTable;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.BizSurveyTableDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyTableConverter.java, v 0.1 2024‐11‐20 1:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSurveyTableConverter extends CommonDOModelConverter<BizSurveyTableDO, BizSurveyTable> {

    @Override
    protected BizSurveyTable safeConvertQuery(BizSurveyTableDO dataObject) {
        BizSurveyTable table = new BizSurveyTable();
        table.setTableId(dataObject.getTableId());
        table.setTitle(dataObject.getTitle());
        table.setDescription(dataObject.getDescription());
        table.setColumn(dataObject.getColumn());
        table.setDataMap(dataObject.getDataMap());
        table.setSurveyId(dataObject.getSurveyId());
        return table;
    }

    @Override
    protected BizSurveyTableDO safeConvertStore(BizSurveyTable model) {
        return null;
    }
}