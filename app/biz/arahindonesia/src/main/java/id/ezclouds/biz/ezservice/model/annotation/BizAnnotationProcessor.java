/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.annotation;

import id.ezclouds.biz.ezservice.config.BizPublicConfig;
import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.common.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAnnotationProcessor.java, v 0.1 2024‐02‐08 5:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAnnotationProcessor {

    public static void annotatePublicConfig(Object object, BizPublicConfig publicConfig) {
        if (object == null) {
            return;
        }

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PublicImageUrl.class)) {
                PublicImageUrl publicImageUrl = field.getAnnotation(PublicImageUrl.class);
                if (AppConstant.Annotation.AVATAR_URL.equals(publicImageUrl.name())) {
                    updateFieldValue(object, field, publicConfig.getAvatarRootImageUrl());
                }

                if (AppConstant.Annotation.IDCARD_URL.equals(publicImageUrl.name())) {
                    updateFieldValue(object, field, publicConfig.getIdCardRootImageUrl());
                }

                if (AppConstant.Annotation.FAMCARD_URL.equals(publicImageUrl.name())) {
                    updateFieldValue(object, field, publicConfig.getFamCardRootImageUrl());
                }
            }
        }
    }

    private static void updateFieldValue(Object object, Field field, String fieldRootUrl) {
        try {
            String fieldName = field.getName();
            String upperFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String stdGetterMethod = "get" + upperFieldName;
            String stdSetterMethod = "set" + upperFieldName;

            String currentValue;
            Method getterMethod = null;
            Method setterMethod = null;
            for(Method method : object.getClass().getMethods()) {
                if (StringUtil.equalsNotNull(stdGetterMethod, method.getName())) {
                    getterMethod = method;
                }
                if (StringUtil.equalsNotNull(stdSetterMethod, method.getName())) {
                    setterMethod = method;
                }
            }

            if (getterMethod != null && setterMethod != null) {
                currentValue = (String) getterMethod.invoke(object);
                String newValue = fieldRootUrl + currentValue;
                setterMethod.invoke(object, newValue);
            }
        } catch (Exception e) {}
    }
}