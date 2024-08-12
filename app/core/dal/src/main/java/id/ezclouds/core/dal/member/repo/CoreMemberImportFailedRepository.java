/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.repo;

import id.ezclouds.core.dal.member.dataobject.CoreMemberImportFailedDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberImportFailedRepository.java, v 0.1 2024‐08‐11 7:37 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberImportFailedRepository extends JpaRepository<CoreMemberImportFailedDO, String> {
    long deleteByOrgIdAndSubOrgId(String orgId, String subOrgId);
}