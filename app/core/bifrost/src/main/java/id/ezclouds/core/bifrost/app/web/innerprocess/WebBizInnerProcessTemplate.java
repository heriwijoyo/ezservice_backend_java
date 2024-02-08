/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.innerprocess;

import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizInnerProcessTemplate.java, v 0.1 2024‐02‐09 12:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebBizInnerProcessTemplate {

    public static void execute(Object request, Handler handler) {
        try {
            handler.onRequestCheck();
            handler.onProcess();
        } catch (EzErrorException ezException) {
            ezException.printStackTrace();
            handler.onEzException(ezException);
        } catch (Exception exception) {
            exception.printStackTrace();
            handler.onEzException(getEzException(exception));
        } finally {
            //TODO:
        }
    }

    private static EzErrorException getEzException(Exception exception) {
        return new EzErrorException(EzErrorCode.SYSTEM_ERROR, "Unknown Error");
    }

    interface Handler {
        void onRequestCheck() throws EzErrorException;
        void onProcess() throws Exception;
        void onEzException(EzErrorException ezException);
    }
}