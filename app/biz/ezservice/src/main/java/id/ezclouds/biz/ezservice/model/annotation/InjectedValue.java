/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: InjectedValue.java, v 0.1 2024‐05‐10 3:55 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectedValue {
    Class<?> type() default String.class;
    String field() default "";
}