/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.processor.inner;

import id.ezclouds.biz.election.service.core.dataobject.BizReportBySubOrgDO;
import id.ezclouds.biz.election.service.core.repo.BizReportBySubOrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSubOrgReportInnerProcessor.java, v 0.1 2024‐07‐24 5:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizSubOrgReportInnerProcessor {

    @Autowired
    private BizReportBySubOrgRepository bizReportBySubOrgRepository;

    @Transactional
    public long deleteAllReport(String orgId) {
        return bizReportBySubOrgRepository.deleteByOrgId(orgId);
    }

    @Transactional
    public void storeReport(BizReportBySubOrgDO reportBySubOrgDO) {
        bizReportBySubOrgRepository.saveAndFlush(reportBySubOrgDO);
    }
}