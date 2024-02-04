/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.dataservice;

import id.ezclouds.biz.arahindonesia.converter.BizModelConverter;
import id.ezclouds.biz.arahindonesia.model.member.BizSubOrganization;
import id.ezclouds.common.dal.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSubOrganizationService.java, v 0.1 2024‐02‐04 9:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppSubOrganizationService {

    @Autowired
    private AppSubOrganizationRepository appSubOrganizationRepository;

    public BizSubOrganization getSubOrganizationById(String subOrgId) {
        return getAllSubOrganization()
                .stream()
                .filter(subOrg -> StringUtil.equalsNotNull(subOrg.getSubOrgId(), subOrgId))
                .findFirst()
                .orElse(null);
    }

    @Cacheable(value = "app_sub_organizations")
    public List<BizSubOrganization> getAllSubOrganization() {
        return appSubOrganizationRepository
                .findAll()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }
}