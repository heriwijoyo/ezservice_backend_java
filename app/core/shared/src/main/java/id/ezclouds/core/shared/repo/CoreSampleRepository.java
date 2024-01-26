/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreSampleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSampleRepository.java, v 0.1 2024‐01‐27 3:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreSampleRepository extends JpaRepository<EzCoreSampleDO, String> {
}