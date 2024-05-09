/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.repo;

import id.ezclouds.biz.ezservice.service.app.dataobject.AppEventDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppEventRepository.java, v 0.1 2024‐04‐10 2:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppEventRepository extends JpaRepository<AppEventDO, String> {

    Page<AppEventDO> findByOrgId(String orgId, Pageable pageable);

    Page<AppEventDO> findByOrgIdAndStatus(String orgId, int status, Pageable pageable);

    AppEventDO findByIdAndOrgId(String id, String orgId);
}