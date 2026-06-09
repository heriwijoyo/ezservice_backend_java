/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app;

import id.ezclouds.biz.election.service.app.dataobject.AppBuildPackageDO;
import id.ezclouds.biz.election.service.app.model.BizAppBuildPackage;
import id.ezclouds.biz.election.service.app.repo.AppBuildPackageRepository;
import id.ezclouds.biz.election.service.app.model.AppBuildType;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.model.core.organization.CoreOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBuildPackageService.java, v 0.1 2024‐06‐16 12:37 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppBuildPackageService {

    @Autowired
    private BizOrganizationService bizOrganizationService;

    @Autowired
    private AppBuildPackageRepository appBuildPackageRepository;

    public List<BizAppBuildPackage> getAllAppBuildPackage() {
        List<String> platforms = Collections.singletonList(AppBuildType.ANDROID.getCode());

        List<BizAppBuildPackage> latestBuildPackages = new ArrayList<>();
        List<CoreOrganization> organizations = bizOrganizationService.getActiveOrganizations();
        for (CoreOrganization organization : organizations) {

            for (String platform : platforms) {
                PageRequest pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdTime"));
                Page<AppBuildPackageDO> result = appBuildPackageRepository
                        .findByOrgIdAndPlatform(organization.getOrgId(), platform, pageRequest);
                if (result != null && result.hasContent()) {
                    latestBuildPackages.add(convert(result.getContent().get(0)));
                }
            }
        }

        return latestBuildPackages;
    }

    public List<BizAppBuildPackage> getAppBuildPackages(String orgId) {
        return appBuildPackageRepository
                .findByOrgId(orgId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public BizAppBuildPackage getLatestBuildPackage(String orgId, String platform) {
        List<BizAppBuildPackage> allBuildPackages = getAllAppBuildPackage();
        for (BizAppBuildPackage buildPackage : allBuildPackages) {
            if (buildPackage.getOrgId().equals(orgId) && buildPackage.getPlatform().equals(platform)) {
                return buildPackage;
            }
        }
        return null;
    }

    public BizAppBuildPackage getBuildPackageByVersionName(String orgId, String platform, String versionName) {
        for (BizAppBuildPackage buildPackage : getAllAppBuildPackage()) {
            if (buildPackage.getOrgId().equals(orgId) && buildPackage.getPlatform().equals(platform) && buildPackage.getVersionName().equals(versionName)) {
                return buildPackage;
            }
        }

        AppBuildPackageDO packageDO = appBuildPackageRepository
                .findByOrgIdAndPlatformAndVersionName(orgId, platform, versionName);
        return convert(packageDO);
    }

    @Transactional
    public void createAppBuildPackage(BizAppBuildPackage buildPackage) throws Exception {
        AppBuildPackageDO buildPackageDO = convert(buildPackage);
        String currentTime = DateUtil.getCurrentFormattedDate();
        buildPackageDO.setId(HashUtil.createHash(buildPackage.getOrgId(), currentTime));
        buildPackageDO.setCreatedTime(currentTime);
        appBuildPackageRepository.saveAndFlush(buildPackageDO);
    }

    private BizAppBuildPackage convert(AppBuildPackageDO buildPackageDO) {
        if (buildPackageDO == null) { return null; }
        BizAppBuildPackage buildPackage = new BizAppBuildPackage();
        buildPackage.setId(buildPackageDO.getId());
        buildPackage.setOrgId(buildPackageDO.getOrgId());
        buildPackage.setPlatform(buildPackageDO.getPlatform());
        buildPackage.setVersionCode(buildPackageDO.getVersionCode());
        buildPackage.setVersionName(buildPackageDO.getVersionName());
        buildPackage.setCreatedTime(buildPackageDO.getCreatedTime());
        buildPackage.setStatus(buildPackageDO.getStatus());
        return buildPackage;
    }

    private AppBuildPackageDO convert(BizAppBuildPackage buildPackage) {
        AppBuildPackageDO buildPackageDO = new AppBuildPackageDO();
        buildPackageDO.setId(buildPackage.getId());
        buildPackageDO.setOrgId(buildPackage.getOrgId());
        buildPackageDO.setPlatform(buildPackage.getPlatform());
        buildPackageDO.setVersionCode(buildPackage.getVersionCode());
        buildPackageDO.setVersionName(buildPackage.getVersionName());
        buildPackageDO.setCreatedTime(buildPackage.getCreatedTime());
        buildPackageDO.setStatus(buildPackage.getStatus());
        return buildPackageDO;
    }
}