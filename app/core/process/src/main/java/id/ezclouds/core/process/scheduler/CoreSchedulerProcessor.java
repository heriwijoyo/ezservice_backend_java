/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.scheduler;

import id.ezclouds.common.facade.biz.BizSchedulerService;
import id.ezclouds.common.facade.process.SchedulerProcessor;
import id.ezclouds.common.model.result.BaseResult;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.core.process.biz.BizProcessorReportGenerateOverall;
import id.ezclouds.core.process.biz.BizProcessorReportUpdateMember;
import id.ezclouds.core.process.model.CoreSchedulerScene;
import id.ezclouds.core.process.report.BizProcessReportDailyReset;
import id.ezclouds.core.process.template.CoreSchedulerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSchedulerProcessor.java, v 0.1 2024‐07‐25 11:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreSchedulerProcessor implements SchedulerProcessor {

    @Autowired
    private BizProcessReportDailyReset bizProcessReportDailyReset;

    @Autowired
    private BizProcessorReportGenerateOverall bizProcessorReportGenerateOverall;

    @Autowired
    private BizProcessorReportUpdateMember bizProcessorReportUpdateMember;

    @Override
    public BaseResult execute(String scene, String param) {

        CoreSchedulerScene schedulerScene = CoreSchedulerScene.getByCode(scene);

        switch (schedulerScene) {
            case UNKNOWN:
                BizSchedulerService bizSchedulerService = BeanFacadeUtil.getBean(BizSchedulerService.class);
                return bizSchedulerService.execute(scene);
            default:
                return executeCoreScheduler(schedulerScene, param);
        }
    }

    private BaseResult executeCoreScheduler(CoreSchedulerScene schedulerScene, String param) {
        return CoreSchedulerTemplate.execute(schedulerScene.getCode(), new CoreSchedulerTemplate.Handler() {
            @Override
            public void preProcess(CoreSchedulerScene schedulerScene) {
            }

            @Override
            public void process(CoreSchedulerScene schedulerScene) {
                switch (schedulerScene) {
                    case REPORT_DAILY_RESET:
                        bizProcessReportDailyReset.process(param);
                        break;
                    case RJL_DAILY_REPORT_OVERALL:
                        bizProcessorReportGenerateOverall.process("RJL0");
                        break;
                    case RJL_HOURLY_MEMBER_TODAY:
                        bizProcessorReportUpdateMember.process("RJL0");
                        break;
                }
            }
        });
    }
}