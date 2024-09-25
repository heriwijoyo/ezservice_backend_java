/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.repo;

import id.ezclouds.core.dal.biz.election.dataobject.BizVoterCanvassOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterCanvassOrderRepository.java, v 0.1 2024‐09‐26 12:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizVoterCanvassOrderRepository extends JpaRepository<BizVoterCanvassOrderDO, String> {
}