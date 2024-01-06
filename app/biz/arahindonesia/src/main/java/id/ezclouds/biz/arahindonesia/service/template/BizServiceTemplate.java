/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.template;

import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.error.EzErrorException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizServiceTemplate.java, v 0.1 2023‐12‐31 1:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizServiceTemplate {

    public static void execute(BizResult bizResult, Handler handler) {
        bizResult.setSuccess(false);

        try {
            handler.onRequestCheck();
            handler.onBizProcess();
        }
        catch (EzErrorException ezException) {
            //TODO: log error
            bizResult.setSuccess(false);
            bizResult.setErrorCode(ezException.getEzErrorCode());
        }
        finally {
            //TODO: log request and result
        }

    }

    public interface Handler {
        void onRequestCheck();
        void onBizProcess();
    }
}