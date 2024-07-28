/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.organization;

import id.ezclouds.common.facade.dal.organization.BizSubOrganizationDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.core.dal.organization.repo.CoreSubOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSubOrganizationDAO.java, v 0.1 2024‐07‐28 9:03 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreSubOrganizationDAO implements BizSubOrganizationDAO {

    @Autowired
    private CoreSubOrganizationRepository coreSubOrganizationRepository;

    @EzDAOLogger
    @Override
    public long countByOrgId(String orgId) {
        return coreSubOrganizationRepository.countByOrgId(orgId);
    }
}