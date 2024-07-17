/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.template;

import id.ezclouds.biz.ezservice.enums.BizSchedulerScene;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSchedulerTemplate.java, v 0.1 2024‐07‐18 2:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSchedulerTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.SCHEDULER);

    public static BizResult execute(String scene, Handler handler) {
        BizResult bizResult = new BizResult();
        BizSchedulerScene schedulerScene = BizSchedulerScene.getByCode(scene);

        String resultCode = "N";
        String errorCode = StringUtil.EMPTY;
        try {
            handler.preProcess(schedulerScene);
            handler.process(schedulerScene);
            resultCode = "Y";
            errorCode = "SUCCESS";
            bizResult.setSuccess(true);
        } catch (Exception e) {
            resultCode = "N";
            if (e instanceof EzErrorException) {
                errorCode = ((EzErrorException)e).getEzErrorCode().getCode();
            } else {
                errorCode = EzErrorCode.SYSTEM_ERROR.getCode();
            }
        } finally {
            String logInfo = schedulerScene.getCode() +
                    "," +
                    resultCode +
                    "," +
                    errorCode;

            LOGGER.info(logInfo);
        }

        return bizResult;
    }

    public interface Handler {
        void preProcess(BizSchedulerScene schedulerScene);
        void process(BizSchedulerScene schedulerScene);
    }
}