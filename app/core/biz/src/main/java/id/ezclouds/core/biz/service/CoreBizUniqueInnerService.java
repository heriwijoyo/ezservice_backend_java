/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service;

import id.ezclouds.common.facade.dal.biz.BizUniqueDAO;
import id.ezclouds.common.model.biz.BizUnique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizUniqueInnerService.java, v 0.1 2024‐08‐31 4:42 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBizUniqueInnerService {

    @Autowired
    private BizUniqueDAO bizUniqueDAO;

    public BizUnique getBizUnique(String uniqueId) {
        return bizUniqueDAO.getUnique(uniqueId);
    }

    @Transactional
    public void storeBizUnique(BizUnique bizUnique) {
        bizUniqueDAO.storeUnique(bizUnique);
    }
}