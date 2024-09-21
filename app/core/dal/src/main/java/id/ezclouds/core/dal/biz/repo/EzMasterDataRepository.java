/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.EzMasterDataDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzMasterDataRepository.java, v 0.1 2024‐09‐04 8:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzMasterDataRepository extends JpaRepository<EzMasterDataDO, String> {

    List<EzMasterDataDO> findByOrgIdAndScene(String orgId, String scene);

    List<EzMasterDataDO> findByOrgIdAndSceneAndDistrictId(String orgId, String scene, String districtId);
}