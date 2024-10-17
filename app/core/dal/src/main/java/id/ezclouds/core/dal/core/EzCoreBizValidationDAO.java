/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core;

import id.ezclouds.common.facade.dal.core.CoreBizValidationDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.core.CoreBizValidation;
import id.ezclouds.core.dal.core.converter.CoreBizValidationConverter;
import id.ezclouds.core.dal.core.repo.CoreBizValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreBizValidationDAO.java, v 0.1 2024‐10‐01 2:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreBizValidationDAO implements CoreBizValidationDAO {

    @Autowired
    private CoreBizValidationRepository coreBizValidationRepository;

    @Override
    @EzDAOLogger
    public CoreBizValidation getBizValidation(String orgId, String scene) {
        return new CoreBizValidationConverter()
                .convertQuery(
                        coreBizValidationRepository
                                .findByOrgIdAndValidationScene(orgId, scene)
                );
    }
}