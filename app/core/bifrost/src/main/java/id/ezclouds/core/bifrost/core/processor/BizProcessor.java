/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.bifrost.core.BaseRequest;
import id.ezclouds.core.shared.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessor.java, v 0.1 2023‐12‐09 3:13 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizProcessor {

    Object process(EzAppEvent event, BaseRequest request) throws EzErrorException;
}