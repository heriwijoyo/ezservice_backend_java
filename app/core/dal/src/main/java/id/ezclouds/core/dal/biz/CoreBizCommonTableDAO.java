/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.core.dal.biz.converter.BizCommonTableConverter;
import id.ezclouds.core.dal.biz.repo.EzBizCommonTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizCommonTableDAO.java, v 0.1 2024‐08‐16 1:39 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizCommonTableDAO implements BizCommonTableDAO {

    @Autowired
    private EzBizCommonTableRepository ezBizCommonTableRepository;

    @EzDAOLogger
    @Override
    public void store(BizCommonTable bizCommonTable) {
        ezBizCommonTableRepository
                .saveAndFlush(new BizCommonTableConverter().convertStore(bizCommonTable));
    }

    @EzDAOLogger
    @Override
    public BizCommonTable getByCode(String orgId, String code) {
        return new BizCommonTableConverter().convertQuery(
                ezBizCommonTableRepository
                        .findByOrgIdAndCode(orgId, code)
        );
    }
}