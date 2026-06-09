/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.subbiz.arahindonesia.service;

import id.ezclouds.biz.election.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.election.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.biz.election.converter.BizModelConverter;
import id.ezclouds.biz.election.service.inner.service.BizPageQueryStrategy;
import id.ezclouds.biz.election.service.request.BizPageRequest;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.core.sequence.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.organization.Organization;
import id.ezclouds.common.model.result.BizPageInfo;
import id.ezclouds.biz.election.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.core.shared.util.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private CoreSequenceService coreSequenceService;

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    public BizSubOrganization getSubOrganizationById(String subOrgId) {
        return getAllSubOrganization()
                .stream()
                .filter(subOrg -> StringUtil.equalsNotNull(subOrg.getSubOrgId(), subOrgId))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public String create(String name, String address, String orgId, String orgCode) {
        String subOrgId = coreSequenceService
                .generateSequence(new Organization(orgId, orgCode), CoreSeqSceneEnum.BIZ_SUB_ORG);
        BizSubOrganizationDO bizSubOrganizationDO = new BizSubOrganizationDO();
        bizSubOrganizationDO.setName(name);
        bizSubOrganizationDO.setOrgId(orgId);
        bizSubOrganizationDO.setSubOrgId(subOrgId);
        bizSubOrganizationDO.setAddress(address);
        bizSubOrganizationDO.setStatus(1);
        bizSubOrganizationDO.setCreatedTime(DateUtil.getCurrentFormattedDate());
        bizSubOrganizationDO.setModifiedTime(DateUtil.getCurrentFormattedDate());
        appSubOrganizationRepository.saveAndFlush(bizSubOrganizationDO);
        return subOrgId;
    }

    @Transactional
    public String createSubOrganization(BizSubOrganization subOrganization) {
        return create(
                subOrganization.getName(),
                subOrganization.getAddress(),
                subOrganization.getOrgId(),
                subOrganization.getOrgCode()
        );
    }

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

    public Map<String, String> getSubOrgNameMap(String orgId) {
        Map<String, String> map = new HashMap<>();
        getSubOrganizationByOrgId(orgId)
                .forEach(subOrganization -> {
                    map.put(subOrganization.getSubOrgId(), subOrganization.getName());
                });
        return map;
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