/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.util;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.shared.context.EzAppContext;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.slf4j.Logger;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DigestLogUtil.java, v 0.1 2024‐01‐28 3:56 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class DigestLogUtil {

    public static void logDigest(Logger logger, DigestLog digestLog) {
        EzAppContext ezAppContext = EzAppContextHolder.getContext();

        String infoLog = "[" +
                ezAppContext.getTraceId() +
                "][" +
                logger.getName() +
                "," +
                ezAppContext.getEzAppEvent().getEventCode() +
                "," +
                ezAppContext.getTimeCost() +
                "," +
                digestLog.getSuccessFlag() +
                "," +
                digestLog.getResultCode() +
                "][appVersionNo=" +
                ezAppContext.getAppVersionNo() +
                "][" +
                digestLog.getErrorMessage() +
                "][" +
                digestLog.getDigestMessage() +
                "]";
        logger.info(infoLog);

        if (StringUtil.isNotBlank(ezAppContext.getErrorStackTrace())) {
            String errorLog = "[" + ezAppContext.getTraceId() + "] - " + ezAppContext.getErrorStackTrace();
            logger.error(errorLog);
        }
    }
}