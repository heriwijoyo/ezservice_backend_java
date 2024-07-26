/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.scheduler;

import id.ezclouds.common.facade.biz.BizSchedulerService;
import id.ezclouds.common.facade.process.SchedulerProcessor;
import id.ezclouds.common.model.result.BaseResult;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSchedulerProcessor.java, v 0.1 2024‐07‐25 11:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreSchedulerProcessor implements SchedulerProcessor {

    @Override
    public BaseResult execute(String scene) {
        BizSchedulerService bizSchedulerService = BeanFacadeUtil.getBean(BizSchedulerService.class);
        return bizSchedulerService.execute(scene);
    }
}