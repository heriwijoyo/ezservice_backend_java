/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.template;

import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.error.EzErrorCode;
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
            bizResult.setSuccess(false);
            bizResult.setErrorCode(ezException.getEzErrorCode());
            bizResult.setErrorMessage(ezException.getErrorMessage());

            //TODO: log error
            ezException.printStackTrace();
        } catch (Exception exception) {
            bizResult.setSuccess(false);
            bizResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
            bizResult.setErrorMessage(EzErrorCode.SYSTEM_ERROR.getDescription());

            //TODO: log error
            exception.printStackTrace();
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