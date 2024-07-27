/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAsyncProcessor.java, v 0.1 2024‐07‐28 4:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class BizAsyncProcessor implements BizProcessor {

    private final int ON_FINISH_BY_PROCESS = 0;
    private final int ON_FINISH_BY_TIMEOUT = 1;


}