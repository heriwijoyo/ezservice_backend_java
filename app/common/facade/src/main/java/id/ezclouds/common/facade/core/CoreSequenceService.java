/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.core;

import id.ezclouds.common.model.core.sequence.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.organization.Organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceService.java, v 0.1 2024‐09‐22 6:05 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreSequenceService {

    void initSequenceConfig(String orgId);

    String generateSequence(Organization organization, CoreSeqSceneEnum seqScene);
}