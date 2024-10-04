/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PublicImageUrl.java, v 0.1 2024‐02‐08 12:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PublicImageUrl {
    String name() default "";
}