/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.template;

import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.dao.DataIntegrityViolationException;

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

            //TODO: log exception
            ezException.printStackTrace();
        } catch (DataIntegrityViolationException exception) {
            bizResult.setSuccess(false);
            bizResult.setErrorCode(EzErrorCode.IDEMPOTENT_ERROR);
            bizResult.setErrorMessage(EzErrorCode.IDEMPOTENT_ERROR.getDescription());
        } catch (Exception exception) {
            bizResult.setSuccess(false);
            bizResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
            bizResult.setErrorMessage(EzErrorCode.SYSTEM_ERROR.getDescription());

            //TODO: log exception
            exception.printStackTrace();
        }
        finally {
            //TODO: log request and result
        }

    }

    public interface Handler {
        void onRequestCheck() throws EzErrorException;
        void onBizProcess() throws EzErrorException;
    }
}