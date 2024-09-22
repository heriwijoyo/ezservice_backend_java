/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.processor.parser;

import id.ezclouds.biz.election.model.annotation.InjectedValue;
import id.ezclouds.common.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ParserModelInjector.java, v 0.1 2024‐05‐10 3:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ParserModelInjector {

    public static void injectValue(Object object, Map<String, Object> valueMap) {
        if (object == null || valueMap == null || valueMap.isEmpty()) {
            return;
        }

        List<Field> injectedFields = getAllInjectedFields(new ArrayList<>(), object.getClass());

        for (Field field : injectedFields) {
            if (field.isAnnotationPresent(InjectedValue.class)) {
                InjectedValue injectedValue = field.getAnnotation(InjectedValue.class);

                Object objectValue = valueMap.get(field.getName());
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

    private static List<Field> getAllInjectedFields(List<Field> fields, Class<?> type) {
        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectedValue.class)) {
                fields.add(field);
            }
        }

        if (type.getSuperclass() != null) {
            getAllInjectedFields(fields, type.getSuperclass());
        }
        return fields;
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