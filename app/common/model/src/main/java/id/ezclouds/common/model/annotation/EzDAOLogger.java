/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.annotation;

import java.lang.annotation.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzDAOLogger.java, v 0.1 2024‐07‐26 2:00 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EzDAOLogger {
    String value() default "";
}