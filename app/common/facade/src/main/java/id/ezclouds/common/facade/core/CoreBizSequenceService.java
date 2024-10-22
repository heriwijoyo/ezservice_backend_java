/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.core;

import id.ezclouds.common.model.core.sequence.CoreBizSeqScene;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizSequenceService.java, v 0.1 2024‐09‐28 1:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreBizSequenceService {

    String generateSequence(CoreBizSeqScene bizSeqScene);
}