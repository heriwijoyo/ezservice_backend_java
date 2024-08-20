/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process;

import id.ezclouds.common.facade.process.SyncProcessExecutor;
import id.ezclouds.common.model.process.ProcessName;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSyncProcessExecutor.java, v 0.1 2024‐08‐19 8:16 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreSyncProcessExecutor implements SyncProcessExecutor {

    @Override
    public void execute(ProcessName processName, String param) {

    }
}