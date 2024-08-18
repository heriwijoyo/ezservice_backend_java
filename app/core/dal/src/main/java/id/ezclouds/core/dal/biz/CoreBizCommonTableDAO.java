/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.model.util.PageResultUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.dal.biz.converter.BizCommonTableConverter;
import id.ezclouds.core.dal.biz.dataobject.EzBizCommonTableDO;
import id.ezclouds.core.dal.biz.repo.EzBizCommonTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public PageResult<BizCommonTable> getCommonTables(WebBizPageRequest request) {

        Page<EzBizCommonTableDO> findResult;
        if (StringUtil.isNotBlank(request.getSearchKeyword())) {
            findResult = ezBizCommonTableRepository
                    .findByCodeContains(request.getSearchKeyword(), request.toPageRequest());
        } else {
            findResult = ezBizCommonTableRepository
                    .findAll(request.toPageRequest());
        }
        return PageResultUtil.convertFindResult(findResult, new BizCommonTableConverter());
    }

    @EzDAOLogger
    @Override
    public void store(BizCommonTable bizCommonTable) {
        ezBizCommonTableRepository
                .saveAndFlush(new BizCommonTableConverter().convertStore(bizCommonTable));
    }

    @EzDAOLogger
    @Override
    public BizCommonTable getByTableId(String tableId) {
        return new BizCommonTableConverter().convertQuery(
                ezBizCommonTableRepository
                .findById(tableId)
                .orElse(null)
        );
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