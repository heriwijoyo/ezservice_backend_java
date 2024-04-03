/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service;

import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.integration.request.ConnectRequest;
import id.ezclouds.core.integration.result.EzConnectResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ConnectServiceTemplate.java, v 0.1 2024‐02‐05 1:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class ConnectServiceTemplate {

    public static void execute(ConnectRequest request, EzConnectResult result, Handler handler) {
        try {
            handler.onRequestCheck();
            handler.onProcess();
        } catch (EzErrorException ezException) {
            result.setErrorCode(ezException.getEzErrorCode());
        } catch (Exception exception) {
            result.setErrorCode(EzErrorCode.SYSTEM_ERROR);
        } finally {
            ConnectServiceLogger.logRequest(request);
        }
    }

    interface Handler {
        void onRequestCheck() throws EzErrorException;
        void onProcess() throws Exception;
    }
}