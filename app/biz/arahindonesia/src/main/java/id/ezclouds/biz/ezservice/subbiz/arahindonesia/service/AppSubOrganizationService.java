/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.subbiz.arahindonesia.service;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.BizMemberDO;
import id.ezclouds.biz.ezservice.service.dataservice.repo.BizMemberRepository;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.enums.CoreSequenceScene;
import id.ezclouds.core.shared.service.CoreSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    private BizMemberRepository bizMemberRepository;

    @Autowired
    private CoreSequenceService coreSequenceService;

    public BizSubOrganization getSubOrganizationById(String subOrgId) {
        return getAllSubOrganization()
                .stream()
                .filter(subOrg -> StringUtil.equalsNotNull(subOrg.getSubOrgId(), subOrgId))
                .findFirst()
                .orElse(null);
    }

    public BizSubOrganization getSubOrganization(String orgId, String memberId) {
        BizMemberDO bizMemberDO = bizMemberRepository
                .findByMemberIdAndOrgId(memberId, orgId);
        if (bizMemberDO == null || StringUtil.isBlank(bizMemberDO.getSubOrgId())) {
            return null;
        }
        return getSubOrganizationById(bizMemberDO.getSubOrgId());
    }

    @Transactional
    public void create(String name, String orgId, String orgCode) {
        String subOrgId = coreSequenceService.generateSequence(orgId, orgCode, CoreSequenceScene.BIZ_SUB_ORG.getCode());
        BizSubOrganizationDO bizSubOrganizationDO = new BizSubOrganizationDO();
        bizSubOrganizationDO.setName(name);
        bizSubOrganizationDO.setOrgId(orgId);
        bizSubOrganizationDO.setSubOrgId(subOrgId);
        bizSubOrganizationDO.setStatus(1);
        appSubOrganizationRepository.saveAndFlush(bizSubOrganizationDO);
    }

    @Cacheable(value = "appSubOrganization")
    public List<BizSubOrganization> getAllSubOrganization() {
        return appSubOrganizationRepository
                .findAll()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }
}