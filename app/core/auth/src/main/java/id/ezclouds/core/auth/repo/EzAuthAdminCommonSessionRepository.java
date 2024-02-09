/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.repo;

import id.ezclouds.core.auth.dataobject.EzAuthAdminCommonSessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthAdminCommonSessionRepository.java, v 0.1 2024‐02‐10 4:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzAuthAdminCommonSessionRepository extends JpaRepository<EzAuthAdminCommonSessionDO, String> {
}