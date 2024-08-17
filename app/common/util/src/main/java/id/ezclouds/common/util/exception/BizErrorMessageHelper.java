/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util.exception;

import id.ezclouds.common.util.constant.BizErrorMessageConstant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizErrorMessageHelper.java, v 0.1 2024‐08‐17 6:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizErrorMessageHelper {

    public static String getBizErrorMessage(EzErrorCode ezErrorCode) {
        switch (ezErrorCode) {
            // Member Login
            case MEMBER_CLIENT_NOT_FOUND:
                return BizErrorMessageConstant.MEMBER_LOGIN_MESSAGE_NOT_FOUND;
            case MEMBER_CLIENT_FROZEN:
            case MEMBER_CLIENT_NOT_ACTIVE:
                //TODO: revisit the usage of abnormal status
            case MEMBER_CLIENT_ABNORMAL:
                return BizErrorMessageConstant.MEMBER_LOGIN_MESSAGE_SUSPEND;
            case MEMBER_LOGIN_FAILED:
                return BizErrorMessageConstant.MEMBER_LOGIN_MESSAGE_FAILED;

            case MEMBER_UNAUTHORIZED:
                return BizErrorMessageConstant.MEMBER_UNAUTHORIZED;

            //Session Error
            case SESSION_INVALID:
                return BizErrorMessageConstant.SESSION_INVALID;
            case SESSION_UNAVAILABLE:
                return BizErrorMessageConstant.SESSION_UNAVAILABLE;
            case SESSION_EXPIRED:
                return BizErrorMessageConstant.SESSION_EXPIRED;
            case SESSION_VERIFY_FAILED:
                return BizErrorMessageConstant.SESSION_VERIFY_FAILED;

            // Multipart Common Upload
            case MULTIPARTFILE_EMPTY:
                return BizErrorMessageConstant.MULTIPARTFILE_EMPTY;
            case MULTIPARTFILE_TYPE_UNSUPPORTED:
                return BizErrorMessageConstant.MULTIPARTFILE_TYPE_UNSUPPORTED;

            // General Error
            case ILLEGAL_ACTION:
                return BizErrorMessageConstant.ILLEGAL_ACTION;
            case ILLEGAL_PARAM:
                return "Param Illegal";
            case DATA_NOT_FOUND:
                return "Data not found";
            default:
                return ezErrorCode.getDescription();
        }
    }
}