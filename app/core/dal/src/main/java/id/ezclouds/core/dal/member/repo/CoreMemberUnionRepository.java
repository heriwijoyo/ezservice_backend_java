/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.repo;

import id.ezclouds.core.dal.member.dataobject.CoreMemberUnionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberUnionRepository.java, v 0.1 2024‐07‐28 6:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberUnionRepository extends JpaRepository<CoreMemberUnionDO, String> {
    long countByOrgId(String orgId);
}