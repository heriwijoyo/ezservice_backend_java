/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service;

import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.integration.request.IntegrationRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreIntegrationServiceTemplate.java, v 0.1 2024‐02‐05 1:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreIntegrationServiceTemplate {

    public static void executeAsync(IntegrationRequest request, Handler handler) {
        try {
            handler.onRequestCheck();
            handler.onProcess();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            //TODO: add logging here
        }

    }

    interface Handler {
        void onRequestCheck() throws EzErrorException;
        void onProcess() throws Exception;
    }
}