/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.repo;

import id.ezclouds.core.dal.biz.election.dataobject.BizElectionVoterDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizElectionVoterRepository.java, v 0.1 2024‐09‐23 11:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizElectionVoterRepository extends JpaRepository<BizElectionVoterDO, String> {
}