/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.repo;

import id.ezclouds.core.dal.auth.dataobject.AuthMemberClientSessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthMemberClientSessionRepository.java, v 0.1 2024‐09‐30 1:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AuthMemberClientSessionRepository extends JpaRepository<AuthMemberClientSessionDO, String> {
}