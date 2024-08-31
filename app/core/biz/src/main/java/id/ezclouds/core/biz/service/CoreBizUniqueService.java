/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service;

import id.ezclouds.common.facade.biz.BizUniqueService;
import id.ezclouds.common.model.biz.BizUnique;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizUniqueService.java, v 0.1 2024‐08‐31 3:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBizUniqueService implements BizUniqueService {

    @Autowired
    private CoreBizUniqueInnerService coreBizUniqueInnerService;

    @Override
    @Transactional
    public BizResult checkUniqueAndTouch(String orgId, String scenario, String param) {
        BizResult result = new BizResult();
        String bizUniqueId = HashUtil.createHash(orgId, scenario, param);

        BizUnique bizUnique = coreBizUniqueInnerService.getBizUnique(bizUniqueId);
        if (bizUnique != null) {
            result.setErrorCode(EzErrorCode.BIZ_UNIQUE_FAILED);
            result.setSuccess(false);
        } else {
            bizUnique = new BizUnique();
            bizUnique.setBizUniqueId(bizUniqueId);
            bizUnique.setOrgId(orgId);
            bizUnique.setBizScenario(scenario);
            bizUnique.setParamValue(param);
            bizUnique.setCreatedTime(DateUtil.getCurrentFormattedDate());

            try {
                coreBizUniqueInnerService.storeBizUnique(bizUnique);
            } catch (Exception ignored) {}

            result.setSuccess(true);
        }

        return result;
    }
}