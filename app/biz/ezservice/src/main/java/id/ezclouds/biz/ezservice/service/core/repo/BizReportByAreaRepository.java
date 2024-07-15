/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core.repo;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportByAreaDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportByAreaRepository.java, v 0.1 2024‐07‐15 11:38 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportByAreaRepository extends JpaRepository<BizReportByAreaDO, String> {
}