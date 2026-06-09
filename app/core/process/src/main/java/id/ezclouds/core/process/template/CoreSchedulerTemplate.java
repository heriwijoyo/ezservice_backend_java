/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.template;

import id.ezclouds.common.model.result.BaseResult;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.process.model.CoreSchedulerScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSchedulerTemplate.java, v 0.1 2024‐07‐28 6:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreSchedulerTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.SCHEDULER);

    public static BaseResult execute(String scene, Handler handler) {
        BaseResult baseResult = new BaseResult();
        CoreSchedulerScene schedulerScene = CoreSchedulerScene.getByCode(scene);

        try {
            handler.preProcess(schedulerScene);
            handler.process(schedulerScene);
            baseResult.setSuccess(true);
        } catch (Exception e) {
            String resultCode;
            if (e instanceof EzErrorException) {
                resultCode = ((EzErrorException)e).getEzErrorCode().getCode();
            } else {
                resultCode = EzErrorCode.SYSTEM_ERROR.getCode();
            }
            baseResult.setSuccess(false);
            baseResult.setResultCode(resultCode);
        } finally {
            String logInfo = schedulerScene.getCode() +
                    "," +
                    baseResult.getSuccessCode() +
                    "," +
                    baseResult.getResultCode();

            LOGGER.info(logInfo);
        }

        return baseResult;
    }

    public interface Handler {
        void preProcess(CoreSchedulerScene schedulerScene);
        void process(CoreSchedulerScene schedulerScene);
    }
}