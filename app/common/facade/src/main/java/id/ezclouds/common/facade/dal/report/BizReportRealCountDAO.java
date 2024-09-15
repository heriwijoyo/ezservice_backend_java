/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.report;

import id.ezclouds.common.model.report.BizReportRealCount;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportRealCountDAO.java, v 0.1 2024‐09‐15 11:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportRealCountDAO {

    void store(BizReportRealCount reportRealCount);

    BizReportRealCount getAndLock(String orgId, String scene, String sceneId);

    BizReportRealCount getAndLock(String orgId, String scene, String sceneId, String sceneParent);

    List<BizReportRealCount> getByScene(String orgId, String scene);

    List<BizReportRealCount> getByScene(String orgId, String scene, String sceneParent);
}