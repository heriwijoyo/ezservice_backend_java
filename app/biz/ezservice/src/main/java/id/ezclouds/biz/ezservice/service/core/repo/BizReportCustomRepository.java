/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core.repo;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportCustomRepository.java, v 0.1 2024‐07‐12 8:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportCustomRepository extends JpaRepository<BizReportCustomDO, String> {

    List<BizReportCustomDO> findByOrgIdAndGroupType(String orgId, String groupType);
}