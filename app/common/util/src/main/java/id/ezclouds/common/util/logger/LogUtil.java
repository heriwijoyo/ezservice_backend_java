/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util.logger;

import org.slf4j.Logger;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: LogUtil.java, v 0.1 2024‐08‐24 1:27 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class LogUtil {

    public static void info(Logger logger, String... args) {
        logger.info(String.join(",", args));
    }
}