/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.common.util.exception.EzErrorCode;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizBaseService.java, v 0.1 2024‐02‐04 1:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizBaseService {

    protected String getBizErrorMessage(EzErrorCode ezErrorCode) {
        switch (ezErrorCode) {
            // Member Login
            case MEMBER_CLIENT_NOT_FOUND:
                return AppConstant.MEMBER_LOGIN_MESSAGE_NOT_FOUND;
            case MEMBER_CLIENT_FROZEN:
            case MEMBER_CLIENT_NOT_ACTIVE:
                //TODO: revisit the usage of abnormal status
            case MEMBER_CLIENT_ABNORMAL:
                return AppConstant.MEMBER_LOGIN_MESSAGE_SUSPEND;
            case MEMBER_LOGIN_FAILED:
                return AppConstant.MEMBER_LOGIN_MESSAGE_FAILED;

            //Session Error
            case SESSION_INVALID:
                return AppConstant.BizMessage.SESSION_INVALID;
            case SESSION_UNAVAILABLE:
                return AppConstant.BizMessage.SESSION_UNAVAILABLE;
            case SESSION_EXPIRED:
                return AppConstant.BizMessage.SESSION_EXPIRED;
            case SESSION_VERIFY_FAILED:
                return AppConstant.BizMessage.SESSION_VERIFY_FAILED;


            default:
                return AppConstant.MESSAGE_SYSTEM_ABNORMAL;
        }
    }
}