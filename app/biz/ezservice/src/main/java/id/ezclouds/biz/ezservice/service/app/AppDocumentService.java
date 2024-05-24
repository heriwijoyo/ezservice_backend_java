/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.service.app.dataobject.AppDocumentDO;
import id.ezclouds.biz.ezservice.service.app.repo.AppDocumentRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppDocumentService.java, v 0.1 2024‐05‐25 3:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppDocumentService {

    @Autowired
    private AppDocumentRepository appDocumentRepository;

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
}