/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizFormDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.form.CoreBizForm;
import id.ezclouds.core.dal.biz.converter.CoreBizFormConverter;
import id.ezclouds.core.dal.biz.repo.CoreBizFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizFormDAO.java, v 0.1 2024‐11‐18 7:16 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizFormDAO implements BizFormDAO {

    @Autowired
    private CoreBizFormRepository coreBizFormRepository;

    @Override
    @EzDAOLogger
    public CoreBizForm getById(String formId) {
        return new CoreBizFormConverter()
                .convertQuery(
                        coreBizFormRepository
                                .findById(formId)
                                .orElse(null)
                );
    }
}