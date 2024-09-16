/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.repo;

import id.ezclouds.core.dal.report.dataobject.CoreReportRealCountDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportRealCountRepository.java, v 0.1 2024‐09‐15 11:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreReportRealCountRepository extends JpaRepository<CoreReportRealCountDO, String> {

    List<CoreReportRealCountDO> findByOrgIdAndScene(String orgId, String scene);

    List<CoreReportRealCountDO> findByOrgIdAndSceneAndSceneParent(String orgId, String scene, String sceneParent);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT crrc FROM CoreReportRealCountDO crrc WHERE crrc.orgId = ?1 AND crrc.scene = ?2 AND crrc.sceneId = ?3")
    CoreReportRealCountDO findAndLock(String orgId, String scene, String sceneId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT crrc FROM CoreReportRealCountDO crrc WHERE crrc.orgId = ?1 AND crrc.scene = ?2 AND crrc.sceneId = ?3 AND crrc.sceneParent = ?4")
    CoreReportRealCountDO findAndLock(String orgId, String scene, String sceneId, String sceneParent);


}