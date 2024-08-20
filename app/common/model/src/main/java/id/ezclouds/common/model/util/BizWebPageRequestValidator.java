/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizWebPageRequestValidator.java, v 0.1 2024‐08‐18 12:57 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizWebPageRequestValidator {

    public static void validate(WebBizPageRequest request) throws EzErrorException {
        AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notNull(request.getPageNumber(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notNull(request.getPageSize(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isTrue(request.getPageNumber() > 0, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isTrue(request.getPageSize() > 0, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSessionId(), EzErrorCode.SESSION_INVALID);
    }
}