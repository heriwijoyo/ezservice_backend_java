/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.repo;

import id.ezclouds.core.auth.dataobject.CoreMemberClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberClientRepository.java, v 0.1 2024‐01‐07 9:40 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberClientRepository extends JpaRepository<CoreMemberClientDO, String> {
}