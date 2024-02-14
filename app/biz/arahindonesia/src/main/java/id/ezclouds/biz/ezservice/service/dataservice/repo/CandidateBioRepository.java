/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.repo;

import id.ezclouds.biz.ezservice.service.dataservice.dataobject.CandidateBioDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateBioRepository.java, v 0.1 2023‐12‐10 3:56 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CandidateBioRepository extends JpaRepository<CandidateBioDO, String> {

    @Query("SELECT bio FROM CandidateBioDO bio WHERE bio.status = 1 ORDER BY bio.order")
    List<CandidateBioDO> getActiveCandidateBios();
}