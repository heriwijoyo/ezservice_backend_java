/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core.repo;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportFailedDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberImportFailedRepository.java, v 0.1 2024‐07‐08 8:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizMemberImportFailedRepository extends JpaRepository<BizMemberImportFailedDO, String> {

    List<BizMemberImportFailedDO> findByOrgIdAndSourceId(String orgId, String sourceId);
}