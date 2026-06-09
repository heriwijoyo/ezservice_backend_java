/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz;

import id.ezclouds.common.model.result.BaseResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSchedulerService.java, v 0.1 2024‐07‐26 8:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizSchedulerService {

    BaseResult execute(String scene);
}