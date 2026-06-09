/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.repo;

import id.ezclouds.core.dal.core.dataobject.CoreBizValidationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizValidationRepository.java, v 0.1 2024‐10‐01 2:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreBizValidationRepository extends JpaRepository<CoreBizValidationDO, String> {

    CoreBizValidationDO findByOrgIdAndValidationScene(String orgId, String validationScene);
}