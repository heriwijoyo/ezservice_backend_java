/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizPageLayoutDAO;
import id.ezclouds.core.dal.biz.dataobject.BizPageLayoutDO;
import id.ezclouds.core.dal.biz.repo.BizPageLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizPageLayoutDAO.java, v 0.1 2024‐10‐13 7:04 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizPageLayoutDAO implements BizPageLayoutDAO {

    @Autowired
    private BizPageLayoutRepository bizPageLayoutRepository;

    @Override
    public String getContent(String layoutCode) {
        return bizPageLayoutRepository
                .findById(layoutCode)
                .map(BizPageLayoutDO::getLayoutContent)
                .orElse(null);
    }
}