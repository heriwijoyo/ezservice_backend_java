/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.web;

import id.ezclouds.common.facade.dal.web.EzWebAppContentDAO;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.dal.web.dataobject.EzCoreWebAppContentDO;
import id.ezclouds.core.dal.web.repo.EzCoreWebAppContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreWebAppContentDAO.java, v 0.1 2024‐09‐19 2:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreWebAppContentDAO implements EzWebAppContentDAO {

    @Autowired
    private EzCoreWebAppContentRepository ezCoreWebAppContentRepository;

    @Override
    public String getContentByAssetFileId(String assetFileId) {
        EzCoreWebAppContentDO appContentDO = ezCoreWebAppContentRepository
                .findById(assetFileId)
                .orElse(null);
        return appContentDO != null ? appContentDO.getContent() : StringUtil.EMPTY;
    }
}