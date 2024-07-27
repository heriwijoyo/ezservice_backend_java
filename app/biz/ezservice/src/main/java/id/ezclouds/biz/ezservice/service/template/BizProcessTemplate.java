/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.template;

import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.context.EzAppContextHolder;
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
        String resultCode = "N";
        try {
            AssertUtil.notNull(event, EzErrorCode.ILLEGAL_ACTION);
            AssertUtil.isTrue(event != BizProcessEvent.UNKNOWN, EzErrorCode.ILLEGAL_ACTION);
            handler.doStart(event);

            EzAppContextHolder.init(event);

            boolean result = handler.doProcess(event);
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

            if (event == null) {
                event = BizProcessEvent.UNKNOWN;
            }
            handler.doFinish(event);
        }
    }

    public interface Handler {
        void doStart(BizProcessEvent processEvent);
        boolean doProcess(BizProcessEvent processEvent);
        void doFinish(BizProcessEvent processEvent);
        List<String> getLogData();
    }
}