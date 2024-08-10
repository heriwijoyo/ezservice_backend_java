/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo;

import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSubOrganizationRepository.java, v 0.1 2024‐02‐04 9:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppSubOrganizationRepository extends JpaRepository<BizSubOrganizationDO, String> {

    List<BizSubOrganizationDO> findByOrgId(String orgId);

    Page<BizSubOrganizationDO> findByOrgId(String orgId, Pageable pageable);
}