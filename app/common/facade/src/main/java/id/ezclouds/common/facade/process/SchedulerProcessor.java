/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.process;

import id.ezclouds.common.model.result.BaseResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SchedulerProcessor.java, v 0.1 2024‐07‐25 11:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface SchedulerProcessor {

    BaseResult execute(String scene);
}