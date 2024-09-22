/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.repo;

import id.ezclouds.biz.election.service.app.dataobject.AppBuildPackageDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBuildPackageRepository.java, v 0.1 2024‐04‐06 1:53 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppBuildPackageRepository extends JpaRepository<AppBuildPackageDO, String> {

    List<AppBuildPackageDO> findByOrgId(String orgId);

    AppBuildPackageDO findByOrgIdAndPlatformAndVersionName(String orgId, String platform, String versionName);

    Page<AppBuildPackageDO> findByOrgIdAndPlatform(String orgId, String platform, Pageable pageable);
}