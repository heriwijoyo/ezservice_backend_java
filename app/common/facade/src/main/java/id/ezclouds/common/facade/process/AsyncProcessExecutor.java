/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.process;

import id.ezclouds.common.model.process.ProcessName;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AsyncProcessExecutor.java, v 0.1 2024‐08‐19 8:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AsyncProcessExecutor {
    void execute(ProcessName processName, String param);
}