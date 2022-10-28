/**
 * DANA.id
 * Copyright (c) 2004‐2022 All Rights Reserved.
 */
package id.ezclouds.core.bootstrap;

import org.springframework.boot.SpringApplication;

/**
 * @author Heri Wijoyo (heri.wijoyo@dana.id)
 * @version $Id: EzBootApplication.java, v 0.1 2022‐10‐28 1:08 AM Heri Wijoyo (heri.wijoyo@dana.id) Exp $$ */
public class EzBootApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(EzBootApplication.class, args);
        } catch (Throwable e) {
            throw e;
        }
    }
}