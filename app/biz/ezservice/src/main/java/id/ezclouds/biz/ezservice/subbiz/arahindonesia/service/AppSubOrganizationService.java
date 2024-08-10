/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.subbiz.arahindonesia.service;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.service.app.dataobject.BizMemberDO;
import id.ezclouds.biz.ezservice.service.app.repo.BizMemberRepository;
import id.ezclouds.biz.ezservice.service.core.BizCacheKey;
import id.ezclouds.biz.ezservice.service.inner.service.BizPageQueryStrategy;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.core.shared.result.BizPageInfo;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.enums.CoreSequenceScene;
import id.ezclouds.core.shared.result.PageResult;
import id.ezclouds.core.shared.service.CoreSequenceService;
import id.ezclouds.core.shared.util.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public void create(String name, String address, String orgId, String orgCode) {
        String subOrgId = coreSequenceService.generateSequence(orgId, orgCode, CoreSequenceScene.BIZ_SUB_ORG.getCode());
        BizSubOrganizationDO bizSubOrganizationDO = new BizSubOrganizationDO();
        bizSubOrganizationDO.setName(name);
        bizSubOrganizationDO.setOrgId(orgId);
        bizSubOrganizationDO.setSubOrgId(subOrgId);
        bizSubOrganizationDO.setAddress(address);
        bizSubOrganizationDO.setStatus(1);
        bizSubOrganizationDO.setCreatedTime(DateUtil.getCurrentFormattedDate());
        bizSubOrganizationDO.setModifiedTime(DateUtil.getCurrentFormattedDate());
        appSubOrganizationRepository.saveAndFlush(bizSubOrganizationDO);
    }

    @Transactional
    public void createSubOrganization(BizSubOrganization subOrganization) {
        create(
                subOrganization.getName(),
                subOrganization.getAddress(),
                subOrganization.getOrgId(),
                subOrganization.getOrgCode()
        );
    }

    @Cacheable(BizCacheKey.SUB_ORGANIZATION_ALL)
    public List<BizSubOrganization> getAllSubOrganization() {
        return appSubOrganizationRepository
                .findAll()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<BizSubOrganization> getSubOrganizationByOrgId(String orgId) {
        return getAllSubOrganization()
                .stream()
                .filter(subOrg -> orgId.equals(subOrg.getOrgId()))
                .collect(Collectors.toList());
    }

    public PageResult<BizSubOrganization> getSubOrganizations(String orgId, PageRequest pageRequest) {
        Page<BizSubOrganizationDO> findResult = appSubOrganizationRepository
                .findByOrgId(orgId, pageRequest);

        return PageResultUtil.convertFindResult(findResult, input -> input
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList()));
    }

    public BizPageInfo pageQuery(BizPageRequest request) {
        BizPageQueryStrategy<BizSubOrganizationDO, String, BizSubOrganization> queryStrategy = new BizPageQueryStrategy<>();
        return queryStrategy.pageQuery(appSubOrganizationRepository, request, modelDO -> {
            BizSubOrganization bizSubOrganization = new BizSubOrganization();
            bizSubOrganization.setSubOrgId(modelDO.getSubOrgId());
            bizSubOrganization.setName(modelDO.getName());
            bizSubOrganization.setAddress(modelDO.getAddress());
            bizSubOrganization.setCreatedTime(modelDO.getCreatedTime());
            bizSubOrganization.setStatus(modelDO.getStatus());
            return bizSubOrganization;
        });
    }
}