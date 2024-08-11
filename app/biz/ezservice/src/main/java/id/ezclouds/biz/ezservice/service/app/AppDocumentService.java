/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.service.app.dataobject.AppDocumentDO;
import id.ezclouds.biz.ezservice.service.app.model.AppDocument;
import id.ezclouds.biz.ezservice.service.app.repo.AppDocumentRepository;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.biz.ezservice.util.PageRequestUtil;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.shared.result.BizPageInfo;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.core.shared.util.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppDocumentService.java, v 0.1 2024‐05‐25 3:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppDocumentService {

    @Autowired
    private AppDocumentRepository appDocumentRepository;

    public BizPageInfo<AppDocument> getAppDocuments(String orgId, BizPageRequest request) {
        Page<AppDocumentDO> findResult = appDocumentRepository
                .findByOrgIdAndStatus(orgId, 1, PageRequestUtil.composePageRequest(request));

        BizPageInfo<AppDocument> bizPageInfo = PageResultUtil.composePageInfo(findResult, input -> input
                .stream()
                .map(AppDocumentService::convert)
                .collect(Collectors.toList()));
        return bizPageInfo;
    }

    @Transactional
    public void createDocumentGallery(String orgId, String type, String title, String fileName) {
        String currentTime = DateUtil.getCurrentFormattedDate();
        AppDocumentDO appDocumentDO = new AppDocumentDO();
        appDocumentDO.setId(HashUtil.createHash(orgId, title, currentTime));
        appDocumentDO.setOrgId(orgId);
        appDocumentDO.setType(type);
        appDocumentDO.setTitle(title);
        appDocumentDO.setUrl(fileName);
        appDocumentDO.setCreatedTime(currentTime);
        appDocumentDO.setModifiedTime(currentTime);
        appDocumentDO.setStatus(1);
        appDocumentRepository.saveAndFlush(appDocumentDO);
    }

    public PageResult<AppDocument> getAppDocuments(String orgId, PageRequest pageRequest) {
        Page<AppDocumentDO> findResult = appDocumentRepository
                .findByOrgId(orgId, pageRequest);

        return PageResultUtil.convertFindResult(findResult, input -> input
                .stream()
                .map(AppDocumentService::convert)
                .collect(Collectors.toList()));
    }

    public static AppDocument convert(AppDocumentDO documentDO) {
        if (documentDO == null) { return null; }
        AppDocument appDocument = new AppDocument();
        appDocument.setId(documentDO.getId());
        appDocument.setType(documentDO.getType());
        appDocument.setTitle(documentDO.getTitle());
        appDocument.setDocUrl(documentDO.getUrl());
        appDocument.setCreatedTime(documentDO.getCreatedTime());
        appDocument.setStatus(documentDO.getStatus());
        return appDocument;
    }
}