/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.repo;

import id.ezclouds.core.dal.auth.dataobject.WebPublicSessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPublicSessionRepository.java, v 0.1 2024‐10‐10 12:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface WebPublicSessionRepository extends JpaRepository<WebPublicSessionDO, String> {
}