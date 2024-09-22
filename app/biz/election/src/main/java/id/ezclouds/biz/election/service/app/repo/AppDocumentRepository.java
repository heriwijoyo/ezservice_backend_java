/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.repo;

import id.ezclouds.biz.election.service.app.dataobject.AppDocumentDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppDocumentRepository.java, v 0.1 2024‐05‐25 3:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppDocumentRepository extends JpaRepository<AppDocumentDO, String> {

    Page<AppDocumentDO> findByOrgId(String orgId, Pageable pageable);

    Page<AppDocumentDO> findByOrgIdAndStatus(String orgId, int status, Pageable pageable);
}