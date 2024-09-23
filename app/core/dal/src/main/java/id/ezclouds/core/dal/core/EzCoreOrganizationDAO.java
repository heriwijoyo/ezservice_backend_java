/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core;

import id.ezclouds.common.facade.dal.core.CoreOrganizationDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.core.Organization;
import id.ezclouds.core.dal.core.converter.CoreOrganizationConverter;
import id.ezclouds.core.dal.core.repo.EzCoreOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreOrganizationDAO.java, v 0.1 2024‐09‐23 1:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreOrganizationDAO implements CoreOrganizationDAO {

    @Autowired
    private EzCoreOrganizationRepository ezCoreOrganizationRepository;

    @Override
    @EzDAOLogger
    public Organization getById(String orgId) {
        return new CoreOrganizationConverter().convertQuery(
                ezCoreOrganizationRepository
                        .findById(orgId)
                        .orElse(null)
        );
    }

    @Override
    @EzDAOLogger
    public List<Organization> getOrganizations() {
        CoreOrganizationConverter converter = new CoreOrganizationConverter();
        return ezCoreOrganizationRepository
                .findAll()
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }
}