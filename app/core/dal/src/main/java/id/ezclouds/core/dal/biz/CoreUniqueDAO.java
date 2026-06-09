/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizUniqueDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.BizUnique;
import id.ezclouds.core.dal.biz.converter.BizUniqueConverter;
import id.ezclouds.core.dal.biz.repo.EzBizUniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreUniqueDAO.java, v 0.1 2024‐08‐31 3:20 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreUniqueDAO implements BizUniqueDAO {

    @Autowired
    private EzBizUniqueRepository ezBizUniqueRepository;

    @EzDAOLogger
    @Override
    public BizUnique getUnique(String uniqueId) {
        return new BizUniqueConverter().convertQuery(
                ezBizUniqueRepository
                        .findById(uniqueId)
                        .orElse(null)
        );
    }

    @EzDAOLogger
    @Override
    public void storeUnique(BizUnique bizUnique) {
        ezBizUniqueRepository
                .saveAndFlush(new BizUniqueConverter().convertStore(bizUnique));
    }
}