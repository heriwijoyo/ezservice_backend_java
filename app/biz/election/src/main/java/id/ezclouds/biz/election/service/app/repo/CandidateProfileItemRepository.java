/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.repo;

import id.ezclouds.biz.election.service.app.dataobject.CandidateProfileItemDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateProfileItemRepository.java, v 0.1 2023‐12‐10 3:30 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CandidateProfileItemRepository extends JpaRepository<CandidateProfileItemDO, String> {

    CandidateProfileItemDO findByOrgIdAndSection(String orgId, String section);
}