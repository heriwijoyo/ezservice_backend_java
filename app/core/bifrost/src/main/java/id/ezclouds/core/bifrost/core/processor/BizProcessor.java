/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.common.model.request.api.ApiRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessor.java, v 0.1 2023‐12‐09 3:13 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizProcessor {

    BizResult process(ApiEvent event, ApiRequest request, MultipartFile file) throws EzErrorException;
}