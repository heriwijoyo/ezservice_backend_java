/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.survey.AppCommonDataSurvey;
import id.ezclouds.core.dal.biz.dataobject.EzCommonDataSurveyDO;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppCommonDataSurveyConverter.java, v 0.1 2024‐08‐18 10:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppCommonDataSurveyConverter {

    public static EzCommonDataSurveyDO convert(AppCommonDataSurvey dataSurvey) {
        EzCommonDataSurveyDO dataSurveyDO = new EzCommonDataSurveyDO();

        Map<String, Field> targetFields = new HashMap<>();
        for (Field targetField : dataSurveyDO.getClass().getDeclaredFields()) {
            targetFields.put(targetField.getName(), targetField);
        }

        for (Field sourceField : dataSurvey.getClass().getDeclaredFields()) {
            if (targetFields.get(sourceField.getName()) != null) {
                targetFields.get(sourceField.getName()).setAccessible(true);
                try {
                    targetFields.get(sourceField.getName()).set(dataSurveyDO, sourceField.get(dataSurvey));
                } catch (Exception ignored) {}
            }
        }

        return dataSurveyDO;
    }
}