/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.process;

import id.ezclouds.common.model.request.FileStreamImportRequest;
import id.ezclouds.common.model.result.BaseResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberImportProcessor.java, v 0.1 2024‐08‐11 4:23 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface MemberImportProcessor {
    BaseResult process(FileStreamImportRequest request);
}