/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.repo;

import id.ezclouds.core.dal.member.dataobject.CoreMemberExtensionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtensionRepository.java, v 0.1 2024‐10‐05 1:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberExtensionRepository extends JpaRepository<CoreMemberExtensionDO, String> {
}