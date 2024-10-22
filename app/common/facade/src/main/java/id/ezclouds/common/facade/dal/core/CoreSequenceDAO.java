/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.core;

import id.ezclouds.common.model.core.sequence.CoreSeqScene;
import id.ezclouds.common.model.core.sequence.CoreSequence;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceDAO.java, v 0.1 2024‐09‐22 5:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreSequenceDAO {

    CoreSequence lockSequence(String orgId, CoreSeqScene scene);

    CoreSequence getSequence(String orgId, CoreSeqScene scene);

    void store(CoreSequence coreSequence);
}