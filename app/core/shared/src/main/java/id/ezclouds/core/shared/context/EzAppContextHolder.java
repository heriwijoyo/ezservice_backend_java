/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.context;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBizContextHolder.java, v 0.1 2023‐12‐09 9:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAppContextHolder {

    private static ThreadLocal<EzAppContext> threadLocal = new ThreadLocal<>();

    public static EzAppContext getContext() {
        return threadLocal.get();
    }

    public static void init(EzAppEvent event) {
        threadLocal.remove();
        threadLocal.set(new EzAppContext(event));
    }
}