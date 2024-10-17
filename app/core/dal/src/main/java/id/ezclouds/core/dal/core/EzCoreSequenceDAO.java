/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core;

import id.ezclouds.common.facade.dal.core.CoreSequenceDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.core.CoreSeqScene;
import id.ezclouds.common.model.core.CoreSequence;
import id.ezclouds.core.dal.core.converter.CoreSequenceConverter;
import id.ezclouds.core.dal.core.repo.CoreSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreSequenceDAO.java, v 0.1 2024‐09‐22 5:42 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreSequenceDAO implements CoreSequenceDAO {

    @Autowired
    private CoreSequenceRepository coreSequenceRepository;

    @Override
    @EzDAOLogger
    public CoreSequence lockSequence(String orgId, CoreSeqScene scene) {
        return new CoreSequenceConverter().convertQuery(
                coreSequenceRepository
                        .findAndLockByOrgIdAndScene(orgId, scene.getScene())
        );
    }

    @Override
    @EzDAOLogger
    public CoreSequence getSequence(String orgId, CoreSeqScene scene) {
        return new CoreSequenceConverter().convertQuery(
                coreSequenceRepository
                        .findByOrgIdAndScene(orgId, scene.getScene())
        );
    }

    @Override
    @EzDAOLogger
    public void store(CoreSequence coreSequence) {
        coreSequenceRepository
                .saveAndFlush(new CoreSequenceConverter().convertStore(coreSequence));
    }
}