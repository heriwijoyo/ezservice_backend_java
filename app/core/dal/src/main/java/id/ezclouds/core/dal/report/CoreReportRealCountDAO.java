/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report;

import id.ezclouds.common.facade.dal.report.BizReportRealCountDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.report.BizReportRealCount;
import id.ezclouds.core.dal.report.converter.CoreReportRealCountConverter;
import id.ezclouds.core.dal.report.repo.CoreReportRealCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportRealCountDAO.java, v 0.1 2024‐09‐15 11:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportRealCountDAO implements BizReportRealCountDAO {

    @Autowired
    private CoreReportRealCountRepository coreReportRealCountRepository;

    @EzDAOLogger
    @Override
    public void store(BizReportRealCount reportRealCount) {
        coreReportRealCountRepository
                .saveAndFlush(new CoreReportRealCountConverter().convertStore(reportRealCount));
    }

    @EzDAOLogger
    @Override
    public BizReportRealCount getAndLock(String orgId, String scene, String sceneId) {
        return new CoreReportRealCountConverter()
                .convertQuery(
                        coreReportRealCountRepository
                                .findAndLock(orgId, scene, sceneId)
                );
    }

    @EzDAOLogger
    @Override
    public BizReportRealCount getAndLock(String orgId, String scene, String sceneId, String sceneParent) {
        return new CoreReportRealCountConverter()
                .convertQuery(
                        coreReportRealCountRepository
                                .findAndLock(orgId, scene, sceneId, sceneParent)
                );
    }

    @EzDAOLogger
    @Override
    public List<BizReportRealCount> getByScene(String orgId, String scene) {
        CoreReportRealCountConverter converter = new CoreReportRealCountConverter();
        return coreReportRealCountRepository
                .findByOrgIdAndScene(orgId, scene)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }

    @EzDAOLogger
    @Override
    public List<BizReportRealCount> getByScene(String orgId, String scene, String sceneParent) {
        CoreReportRealCountConverter converter = new CoreReportRealCountConverter();
        return coreReportRealCountRepository
                .findByOrgIdAndSceneAndSceneParent(orgId, scene, sceneParent)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }
}