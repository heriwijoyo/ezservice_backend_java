/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.shared.enums.CoreUniqueScene;
import id.ezclouds.core.shared.repo.CoreUniqueRepository;
import id.ezclouds.core.shared.repo.dataobject.CoreUniqueDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreUniqueService.java, v 0.1 2024‐01‐07 7:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreUniqueService {

    @Autowired
    private CoreUniqueRepository coreUniqueRepository;

    public boolean insertAndCheck(CoreUniqueScene uniqueScene, String orgId, String uniqueValue) {
        boolean result;
        String uniqueId = orgId + uniqueScene.getCode() + uniqueValue;

        CoreUniqueDO coreUniqueDO = new CoreUniqueDO();
        coreUniqueDO.setUniqueId(uniqueId);
        coreUniqueDO.setOrgId(orgId);
        coreUniqueDO.setScene(uniqueScene.getCode());
        coreUniqueDO.setUniqueValue(uniqueValue);
        coreUniqueDO.setCreatedTime(DateUtil.getCurrentFormattedDate());

        try {
            coreUniqueRepository.save(coreUniqueDO);
            result = true;
        } catch (Exception e) {
            result = false;
        }

        return result;
    }
}