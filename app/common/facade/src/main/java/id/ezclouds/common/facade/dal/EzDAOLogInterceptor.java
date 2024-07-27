/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzDAOLogInterceptor.java, v 0.1 2024‐07‐26 3:46 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzDAOLogInterceptor implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("EzDAOLogInterceptor : "+ method.getName());
        return method.invoke(proxy, args);
    }
}