/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.repo;

import id.ezclouds.core.dal.biz.election.dataobject.BizCanvassRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCanvassRecordRepository.java, v 0.1 2024‐09‐26 12:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizCanvassRecordRepository extends JpaRepository<BizCanvassRecordDO, String> {
}