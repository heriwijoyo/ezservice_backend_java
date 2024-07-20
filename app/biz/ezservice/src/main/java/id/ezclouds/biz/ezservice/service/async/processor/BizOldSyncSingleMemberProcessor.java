/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.enums.BizAsyncScene;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.service.async.parser.SyncMemberConverter;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportFailedDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportFailedRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportRepository;
import id.ezclouds.biz.ezservice.service.request.BizAsyncProcessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizOldSyncSingleMemberProcessor.java, v 0.1 2024‐07‐11 10:06 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizOldSyncSingleMemberProcessor implements BizOldAsyncProcessor {

    @Autowired
    private BizMemberImportRepository bizMemberImportRepository;

    @Autowired
    private BizMemberImportFailedRepository bizMemberImportFailedRepository;

    @Override
    public void process(BizAsyncProcessRequest request) {
        if (request == null
                || request.getBizAsyncScene() == null
                || request.getBizAsyncScene() != BizAsyncScene.SYNC_SINGLE_MEMBER_DATA_REGISTER
                || request.getPayload() == null) {
            return;
        }

        Object bizMemberObj = request.getPayload().get("BIZ_MEMBER");
        if (!(bizMemberObj instanceof BizMember)) {
            return;
        }

        BizMember bizMember = (BizMember) bizMemberObj;

        SyncMemberConverter syncMemberConverter = new SyncMemberConverter(
                request.getOrgId(),
                request.getBizAsyncScene().getCode()
        );

        BizMemberImportDO memberImportDO = syncMemberConverter.convert(bizMember);
        try {
            bizMemberImportRepository.saveAndFlush(memberImportDO);
        } catch (Exception e) {
            tryStoreFailedPayload(memberImportDO);
        }
    }

    private void tryStoreFailedPayload(BizMemberImportDO memberImportDO) {
        try {
            BizMemberImportFailedDO importFailedDO = new BizMemberImportFailedDO();
            importFailedDO.setId(memberImportDO.getBizMemberId());
            importFailedDO.setOrgId(memberImportDO.getOrgId());
            importFailedDO.setSourceId(memberImportDO.getSourceId());
            importFailedDO.setPayload(new ObjectMapper().writeValueAsString(memberImportDO));
            bizMemberImportFailedRepository.saveAndFlush(importFailedDO);
        } catch (Exception e) {}
    }
}