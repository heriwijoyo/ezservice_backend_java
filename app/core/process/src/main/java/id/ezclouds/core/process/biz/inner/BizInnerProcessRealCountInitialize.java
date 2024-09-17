/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.dal.report.BizReportRealCountDAO;
import id.ezclouds.common.model.report.BizReportRealCount;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessRealCountInitialize.java, v 0.1 2024‐09‐17 1:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizInnerProcessRealCountInitialize {

    @Autowired
    private BizReportRealCountDAO bizReportRealCountDAO;

    @Transactional
    public void init(String orgId, String scene, String sceneId, String sceneParent, int sort) {
        BizReportRealCount realCount = bizReportRealCountDAO
                .getAndLock(orgId, scene, sceneId, sceneParent);
        if (realCount == null) {
            realCount = new BizReportRealCount();
            realCount.setId(HashUtil.createHash(orgId, scene, sceneId, sceneParent));
            realCount.setOrgId(orgId);
            realCount.setScene(scene);
            realCount.setSceneId(sceneId);
            realCount.setSceneParent(sceneParent);
        }
        realCount.setUpdatedTime(DateUtil.getCurrentFormattedDateMillis());
        realCount.setSort(sort);

        bizReportRealCountDAO
                .store(realCount);
    }
}