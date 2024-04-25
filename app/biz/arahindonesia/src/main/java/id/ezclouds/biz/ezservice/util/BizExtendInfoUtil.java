/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.util;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizExtendInfoUtil.java, v 0.1 2024‐04‐26 4:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizExtendInfoUtil {

    public static void validateExtendInfo(Map<String, String> extendInfo, String... extKeys) throws EzErrorException {
        AssertUtil.notNull(extendInfo, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isTrue(!extendInfo.isEmpty(), EzErrorCode.ILLEGAL_PARAM);
        for (String extKey : extKeys) {
            AssertUtil.notBlank(extendInfo.get(extKey), EzErrorCode.ILLEGAL_PARAM);
        }
    }
}