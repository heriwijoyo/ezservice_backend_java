/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessReportDataInitialize.java, v 0.1 2024‐09‐10 11:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessReportDataInitialize extends BizAsyncProcessor {

    @Override
    public BizProcessEvent getProcessEvent() {
        return null;
    }

    @Override
    protected int maxProcessTime() {
        return 0;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        return false;
    }
}