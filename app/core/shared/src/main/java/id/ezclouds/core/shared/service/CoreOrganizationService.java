/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.core.shared.converter.CoreModelConverter;
import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.repo.CoreOrganizationRepository;
import id.ezclouds.core.shared.repo.dataobject.EzCoreOrganizationDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrganizationService.java, v 0.1 2024‐01‐28 6:16 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreOrganizationService {

    @Autowired
    private CoreOrganizationRepository coreOrganizationRepository;

    public List<CoreOrganization> getActiveOrganizations() {
        return coreOrganizationRepository
                .findAllActiveOrganizations()
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public Page<EzCoreOrganizationDO> getOrganizationAll(PageRequest pageRequest) {
        return coreOrganizationRepository
                .findAll(pageRequest);
    }
}