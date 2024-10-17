/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.core;

import id.ezclouds.common.model.core.CoreBizSequence;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizSequenceDAO.java, v 0.1 2024‐09‐28 1:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreBizSequenceDAO {

    CoreBizSequence lockBizSequence(String orgId, String scene, String sceneId);

    void store(CoreBizSequence bizSequence);
}