/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core.repo;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberImportRepository.java, v 0.1 2024‐07‐07 10:34 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizMemberImportRepository extends JpaRepository<BizMemberImportDO, String> {

    List<BizMemberImportDO> findByOrgId(String orgId);

    List<BizMemberImportDO> findByOrgIdAndSourceId(String orgId, String sourceId);

    List<BizMemberImportDO> findByOrgIdAndSourceIdNot(String orgId, String sourceIdNot);
}