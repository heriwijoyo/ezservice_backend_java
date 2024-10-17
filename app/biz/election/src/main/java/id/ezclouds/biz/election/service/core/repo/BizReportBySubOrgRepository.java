/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.core.repo;

import id.ezclouds.biz.election.service.core.dataobject.BizReportBySubOrgDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportByAreaRepository.java, v 0.1 2024‐07‐15 11:38 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportBySubOrgRepository extends JpaRepository<BizReportBySubOrgDO, String> {

    long deleteByOrgId(String orgId);
}