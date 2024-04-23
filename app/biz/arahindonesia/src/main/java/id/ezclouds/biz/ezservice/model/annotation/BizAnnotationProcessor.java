/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.annotation;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.common.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAnnotationProcessor.java, v 0.1 2024‐02‐08 5:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAnnotationProcessor {

    public static void annotatePublicConfig(Object object, BizPublicUrlResolver publicConfig) {
        if (object == null) {
            return;
        }

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PublicImageUrl.class)) {
                PublicImageUrl publicImageUrl = field.getAnnotation(PublicImageUrl.class);
                switch (publicImageUrl.name()) {
                    case AppConstant.Annotation.AVATAR_URL:
                        updateFieldValue(object, field, publicConfig.getAvatarRootImageUrl());
                        break;
                    case AppConstant.Annotation.IDCARD_URL:
                        updateFieldValue(object, field, publicConfig.getIdCardRootImageUrl());
                        break;
                    case AppConstant.Annotation.FAMCARD_URL:
                        updateFieldValue(object, field, publicConfig.getFamCardRootImageUrl());
                        break;
                    case AppConstant.Annotation.APP_GALLERY_URL:
                        updateFieldValue(object, field, publicConfig.getAppGalleryRootImageUrl());
                        break;
                    case AppConstant.Annotation.NEWS_GALLERY_URL:
                        updateFieldValue(object, field, publicConfig.getNewsGalleryRootImageUrl());
                        break;
                    case AppConstant.Annotation.EVENT_GALLERY_URL:
                        updateFieldValue(object, field, publicConfig.getEventGalleryRootImageUrl());
                        break;
                    case AppConstant.Annotation.OTHER_GALLERY_URL:
                        updateFieldValue(object, field, publicConfig.getOtherGalleryRootImageUrl());
                        break;
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
                if (!currentValue.contains("http")) {
                    String newValue = fieldRootUrl + currentValue;
                    setterMethod.invoke(object, newValue);
                }
            }
        } catch (Exception e) {}
    }
}