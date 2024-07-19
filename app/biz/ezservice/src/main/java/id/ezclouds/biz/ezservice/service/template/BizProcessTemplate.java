/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.template;

import id.ezclouds.biz.ezservice.service.async.event.BizProcessEvent;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessTemplate.java, v 0.1 2024‐07‐15 2:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizProcessTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.ASYNC_PROCESS);

    public static void execute(BizProcessEvent event, Handler handler) {
        EzAppContextHolder.init(event);

        String resultCode = "N";
        try {
            boolean result = handler.onProcess(event);
            resultCode = result ? "Y" : "N";
        } catch (Exception e) {
            resultCode = "E";
            EzAppContextHolder
                    .getContext()
                    .appendErrorStackTrace(ExceptionUtil.getStackTrace(e));
        } finally {
            String traceId = EzAppContextHolder.getContext().getTraceId();
            String timeCost = EzAppContextHolder.getContext().getTimeCost();

            String logInfo = traceId +
                    "," +
                    event.getEventCode() +
                    "," +
                    resultCode +
                    "," +
                    timeCost +
                    "," +
                    String.join(",", handler.getLogData());

            String errorStackTrace = EzAppContextHolder
                    .getContext()
                    .getErrorStackTrace();
            if (StringUtil.isNotBlank(errorStackTrace)) {
                logInfo += "," + errorStackTrace;
            }

            LOGGER.info(logInfo);
            handler.onFinish();
        }
    }

    public interface Handler {
        boolean onProcess(BizProcessEvent processEvent);
        void onFinish();
        List<String> getLogData();
    }
}