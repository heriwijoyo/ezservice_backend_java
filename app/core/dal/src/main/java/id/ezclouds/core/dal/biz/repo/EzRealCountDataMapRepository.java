/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.EzRealCountDataMapDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzRealCountDataMapRepository.java, v 0.1 2024‐09‐17 12:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzRealCountDataMapRepository extends JpaRepository<EzRealCountDataMapDO, String> {

    List<EzRealCountDataMapDO> findByOrgIdAndScene(String orgId, String scene);
}