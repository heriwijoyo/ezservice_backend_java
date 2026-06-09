/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.facade.dal.organization.SubOrganizationDAO;
import id.ezclouds.common.facade.organization.SubOrganizationService;
import id.ezclouds.common.model.core.organization.SubOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSubOrganizationService.java, v 0.1 2024‐08‐11 2:19 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreSubOrganizationService implements SubOrganizationService {

    @Autowired
    private SubOrganizationDAO subOrganizationDAO;

    @Override
    public List<SubOrganization> getSubOrganizationAll(String orgId) {
        return subOrganizationDAO.getByOrgId(orgId);
    }

    @Override
    public List<SubOrganization> getSubOrganizationActive(String orgId) {
        return subOrganizationDAO.getActiveSubOrg(orgId);
    }

    @Override
    public SubOrganization getById(String subOrgId) {
        return subOrganizationDAO
                .getById(subOrgId);
    }
}