/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.repo;

import id.ezclouds.core.dal.auth.dataobject.EzAuthMemberClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthMemberClientRepository.java, v 0.1 2024‐08‐13 11:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzAuthMemberClientRepository extends JpaRepository<EzAuthMemberClientDO, String> {
    EzAuthMemberClientDO findByOrgIdAndAppIdAndLoginTypeAndLoginId(String orgId, String appId, String loginType, String loginId);
}