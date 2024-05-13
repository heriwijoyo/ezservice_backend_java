/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.integration.request.ConnectRequest;
import id.ezclouds.core.integration.result.EzConnectResult;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ConnectServiceLogger.java, v 0.1 2024‐04‐03 3:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ConnectServiceLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.INTEGRATION_SERVICE);

    public static void logRequest(ConnectRequest request) {
        String traceId = StringUtil.EMPTY;
        if (EzAppContextHolder.getContext() != null && EzAppContextHolder.getContext().getTraceId() != null) {
            traceId = EzAppContextHolder.getContext().getTraceId();
        }
        String requestLog = request != null ? request.toString() : "ConnectRequest=NULL";
        LOGGER.info(traceId + " --- " + requestLog);
    }

    public static void logResult(EzConnectResult result) {
        String traceId = StringUtil.EMPTY;
        if (result.getTraceId() != null) {
            traceId = result.getTraceId();
        }
        String resultLog = result.toString();
        LOGGER.info(traceId + " --- " + resultLog);
    }
}