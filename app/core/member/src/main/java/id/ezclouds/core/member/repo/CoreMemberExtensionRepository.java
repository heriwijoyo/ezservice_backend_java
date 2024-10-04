/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.repo;

import id.ezclouds.core.member.dataobject.EzCoreMemberExtensionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtensionRepository.java, v 0.1 2023‐12‐31 11:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberExtensionRepository extends JpaRepository<EzCoreMemberExtensionDO, String> {

    @Query("SELECT me FROM EzCoreMemberExtensionDO me WHERE me.memberId = :memberId")
    EzCoreMemberExtensionDO findByMemberId(@Param("memberId") String memberId);

    List<EzCoreMemberExtensionDO> findByOrgId(String orgId);
}