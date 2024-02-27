/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.shared.repo.CoreUniqueRepository;
import id.ezclouds.core.shared.repo.dataobject.CoreUniqueDO;
import id.ezclouds.core.shared.result.CoreResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreUniqueService.java, v 0.1 2024‐01‐07 7:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreUniqueService {

    @Autowired
    private CoreUniqueRepository coreUniqueRepository;

    @Transactional
    public CoreResult<Boolean> insertUnique(String orgId, String scene, String uniqueValue) {
        CoreResult<Boolean> coreResult = new CoreResult<>();

        String uniqueId = orgId + scene + uniqueValue;
        CoreUniqueDO coreUniqueDO = new CoreUniqueDO();
        coreUniqueDO.setUniqueId(uniqueId);
        coreUniqueDO.setOrgId(orgId);
        coreUniqueDO.setScene(scene);
        coreUniqueDO.setUniqueValue(uniqueValue);
        coreUniqueDO.setCreatedTime(DateUtil.getCurrentFormattedDate());

        try {
            coreUniqueRepository.save(coreUniqueDO);
            coreResult.setSuccess(true);
        } catch (DataIntegrityViolationException integrityException) {
            coreResult.setErrorCode(EzErrorCode.IDEMPOTENT_ERROR);
        } catch (Exception e) {
            coreResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
        }

        return coreResult;
    }

    @Transactional
    public void revertUnique(String orgId, String scene, String uniqueValue) {
        String uniqueId = orgId + scene + uniqueValue;
        CoreUniqueDO coreUniqueDO = new CoreUniqueDO();
        coreUniqueDO.setUniqueId(uniqueId);
        coreUniqueDO.setOrgId(orgId);
        coreUniqueDO.setScene(scene);
        coreUniqueDO.setUniqueValue(uniqueValue);
        coreUniqueRepository.delete(coreUniqueDO);
    }
}