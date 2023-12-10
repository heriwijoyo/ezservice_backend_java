/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.data;

import id.ezclouds.biz.arahindonesia.converter.ModelConverter;
import id.ezclouds.common.dal.OrganizationRepository;
import id.ezclouds.core.shared.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: OrganizationService.java, v 0.1 2023‐12‐04 3:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Cacheable("organizations")
    public List<Organization> getOrganizations() {
        return organizationRepository
                .findAll()
                .stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }
}