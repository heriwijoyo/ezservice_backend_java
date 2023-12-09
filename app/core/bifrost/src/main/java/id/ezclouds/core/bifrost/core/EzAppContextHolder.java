/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core;

import id.ezclouds.common.dal.model.Organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBizContextHolder.java, v 0.1 2023‐12‐09 9:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAppContextHolder {

    private static ThreadLocal<EzAppContext> threadLocal = new ThreadLocal<>();

    public static EzAppContext getContext() {
        return threadLocal.get();
    }

    public static void setContext(EzAppContext context) {
        threadLocal.set(context);
    }

    public static void init(EzAppEvent event) {
        threadLocal.remove();
        threadLocal.set(new EzAppContext(event));
    }

    public static void initWithContext(EzAppContext context) {
        threadLocal.remove();
        threadLocal.set(context);
    }

    public static void setOrganization(Organization organization) {
        threadLocal.get().setOrganization(organization);
    }
}