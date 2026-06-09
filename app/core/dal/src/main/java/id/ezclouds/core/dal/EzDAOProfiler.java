/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.dal.profiler.EzDAOContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzDAOProfiler.java, v 0.1 2024‐07‐26 12:00 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class EzDAOProfiler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.EZDAL);
    private static ThreadLocal<EzDAOContext> threadLocal = new ThreadLocal<>();

    public static void start(String traceId, String orgId, String invokeTarget) {
        threadLocal.remove();

        EzDAOContext ezDAOContext = new EzDAOContext();
        ezDAOContext.setTraceId(traceId);
        ezDAOContext.setOrgId(orgId);
        ezDAOContext.setInvokeTarget(invokeTarget);
        threadLocal.set(ezDAOContext);
    }

    public static void end(String invokeTarget, String successCode, String resultValue) {
        EzDAOContext context = threadLocal.get();
        if (context != null && StringUtil.equalsNotNull(invokeTarget, context.getInvokeTarget())) {
            context.endTimeRecord();
            context.setSuccessCode(successCode);
            context.setResultValue(resultValue);
            LOGGER.info(context.getLogMessage());
        }
    }
}