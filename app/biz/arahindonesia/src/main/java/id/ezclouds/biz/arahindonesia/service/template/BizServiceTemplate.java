/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.template;

import id.ezclouds.biz.arahindonesia.service.request.BizRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.error.EzErrorException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizServiceTemplate.java, v 0.1 2023‐12‐31 1:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizServiceTemplate {

    public static void execute(BizRequest bizRequest, Handler handler) {
        BizResult bizResult;

        try {
            handler.onRequestCheck(bizRequest);
            bizResult = handler.onBizProcess(bizRequest);
        }
        catch (EzErrorException ezException) {
            //TODO: log error
        }
        finally {
            //TODO: log request and result
        }

    }

    public interface Handler<T> {
        void onRequestCheck(BizRequest request);
        BizResult<T> onBizProcess(BizRequest request);
    }
}