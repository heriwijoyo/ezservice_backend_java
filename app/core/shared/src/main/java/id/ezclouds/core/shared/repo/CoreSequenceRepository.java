/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreSequenceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceRepository.java, v 0.1 2023‐12‐30 3:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreSequenceRepository extends JpaRepository<EzCoreSequenceDO, String> {

    @Query(value = "SELECT * FROM ez_core_sequence s WHERE s.org_id = :orgId AND s.scene = :scene FOR UPDATE", nativeQuery = true)
    EzCoreSequenceDO findForUpdateByOrgAndScene(
            @Param("orgId") String orgId,
            @Param("scene") String scene
    );

    @Query(value = "UPDATE ez_core_sequence SET step_value = :stepValue, sequence = :sequence WHERE sequence_id = :seqId", nativeQuery = true)
    @Modifying
    void updateEzCoreSequence(
            @Param("seqId") String seqId,
            @Param("stepValue") int stepValue,
            @Param("sequence") int sequence
    );
}