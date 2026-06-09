/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.core.repo;

import id.ezclouds.biz.election.service.core.dataobject.BizMemberUnionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberUnionDuplicateRepository.java, v 0.1 2024‐07‐15 1:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizMemberUnionDuplicateRepository extends JpaRepository<BizMemberUnionDO, String> {

    long deleteByOrgId(String orgId);
}