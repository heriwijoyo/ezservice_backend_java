/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.EzBizCommonTableDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzBizCommonTableRepository.java, v 0.1 2024‐08‐16 6:11 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzBizCommonTableRepository extends JpaRepository<EzBizCommonTableDO, String> {
    Page<EzBizCommonTableDO> findByCodeContains(String code, Pageable pageable);
    EzBizCommonTableDO findByOrgIdAndCode(String orgId, String code);
    List<EzBizCommonTableDO> findByOrgIdAndPageId(String orgId, String pageId);
}