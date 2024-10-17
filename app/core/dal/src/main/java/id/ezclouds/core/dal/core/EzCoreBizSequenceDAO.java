/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core;

import id.ezclouds.common.facade.dal.core.CoreBizSequenceDAO;
import id.ezclouds.common.model.core.CoreBizSequence;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.dal.core.converter.CoreBizSequenceConverter;
import id.ezclouds.core.dal.core.repo.EzCoreBizSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreBizSequenceDAO.java, v 0.1 2024‐09‐28 1:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreBizSequenceDAO implements CoreBizSequenceDAO {

    @Autowired
    private EzCoreBizSequenceRepository ezCoreBizSequenceRepository;

    @Override
    public CoreBizSequence lockBizSequence(String orgId, String scene, String sceneId) {
        String bizSeqId = HashUtil.createHash(orgId, scene, sceneId);
        return new CoreBizSequenceConverter()
                .convertQuery(
                        ezCoreBizSequenceRepository
                                .findAndLockById(bizSeqId)
                );
    }

    @Override
    public void store(CoreBizSequence bizSequence) {
        ezCoreBizSequenceRepository
                .saveAndFlush(
                        new CoreBizSequenceConverter()
                                .convertStore(bizSequence)
                );
    }
}