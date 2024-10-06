/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.repo;

import id.ezclouds.core.dal.biz.election.dataobject.BizVoterInvalidDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterInvalidRepository.java, v 0.1 2024‐10‐06 10:27 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizVoterInvalidRepository extends JpaRepository<BizVoterInvalidDO, String> {
}