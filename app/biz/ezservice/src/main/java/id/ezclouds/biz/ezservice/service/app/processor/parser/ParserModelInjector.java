/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor.parser;

import id.ezclouds.biz.ezservice.model.annotation.InjectedValue;
import id.ezclouds.common.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ParserModelInjector.java, v 0.1 2024‐05‐10 3:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ParserModelInjector {

    public static void injectValue(Object object, Map<String, Object> valueMap, Map<String, String> configMap) {
        if (object == null || valueMap == null || valueMap.isEmpty() || configMap == null || configMap.isEmpty()) {
            return;
        }

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectedValue.class)) {
                InjectedValue injectedValue = field.getAnnotation(InjectedValue.class);
                String fieldKey = injectedValue.field();

                if (StringUtil.isBlank(fieldKey)) {
                    continue;
                }

                String configMapKey = configMap.get(fieldKey);
                if (StringUtil.isBlank(configMapKey)) {
                    continue;
                }

                Object objectValue = valueMap.get(configMapKey);
                if (objectValue instanceof String) {
                    Object castedValue = objectValue;
                    if (injectedValue.type() == Integer.class) {
                        castedValue = Integer.parseInt((String) objectValue);
                    }
                    updateFieldValue(object, field, castedValue);
                }
            }
        }
    }

    private static void updateFieldValue(Object object, Field field, Object value) {
        try {
            String fieldName = field.getName();
            String upperFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String stdSetterMethod = "set" + upperFieldName;

            Method setterMethod = null;
            for(Method method : object.getClass().getMethods()) {
                if (StringUtil.equalsNotNull(stdSetterMethod, method.getName())) {
                    setterMethod = method;
                }
            }

            if (setterMethod != null) {
                setterMethod.invoke(object, value);
            }
        } catch (Exception e) {}
    }
}