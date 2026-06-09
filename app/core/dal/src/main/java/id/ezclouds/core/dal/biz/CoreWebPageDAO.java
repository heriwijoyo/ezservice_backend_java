/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizWebPageDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.BizWebPage;
import id.ezclouds.core.dal.biz.converter.BizWebPageConverter;
import id.ezclouds.core.dal.biz.repo.EzBizWebPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreWebPageDAO.java, v 0.1 2024‐08‐24 11:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreWebPageDAO implements BizWebPageDAO {

    @Autowired
    private EzBizWebPageRepository ezBizWebPageRepository;

    @EzDAOLogger
    @Override
    public BizWebPage getWebPage(String pageId) {
        return new BizWebPageConverter().convertQuery(
                ezBizWebPageRepository
                        .findById(pageId)
                        .orElse(null)
        );
    }
}