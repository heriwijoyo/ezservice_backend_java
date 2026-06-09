/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.util;

import id.ezclouds.common.util.context.EzAppContextHolder;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizContextUtil.java, v 0.1 2024‐09‐30 1:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizContextUtil {

    public static String getOrgId() {
        if (EzAppContextHolder.getContext() != null) {
            return EzAppContextHolder.getContext().getOrgId();
        }
        return null;
    }

    public static String getAppId() {
        if (EzAppContextHolder.getContext() != null) {
            return EzAppContextHolder.getContext().getAppId();
        }
        return null;
    }

    public static String getClientId() {
        if (EzAppContextHolder.getContext() != null) {
            return EzAppContextHolder.getContext().getClientId();
        }
        return null;
    }

    public static String getMemberSessionId() {
        if (EzAppContextHolder.getContext() != null) {
            return EzAppContextHolder.getContext().getMemberSessionId();
        }
        return null;
    }
}