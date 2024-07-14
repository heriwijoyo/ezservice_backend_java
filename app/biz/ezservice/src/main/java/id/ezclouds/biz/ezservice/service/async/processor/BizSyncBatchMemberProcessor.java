/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.converter.BizMemberConverter;
import id.ezclouds.biz.ezservice.enums.BizAsyncScene;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.service.async.parser.SyncMemberConverter;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportFailedDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportFailedRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportRepository;
import id.ezclouds.biz.ezservice.service.request.BizAsyncProcessRequest;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.service.CoreMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSyncBatchMemberProcessor.java, v 0.1 2024‐07‐11 4:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizSyncBatchMemberProcessor implements BizAsyncProcessor {

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizMemberImportRepository bizMemberImportRepository;

    @Autowired
    private BizMemberImportFailedRepository bizMemberImportFailedRepository;

    @Override
    public void process(BizAsyncProcessRequest request) {
        if (request == null
                || request.getBizAsyncScene() == null
                || request.getBizAsyncScene() != BizAsyncScene.SYNC_BULK_MEMBER_DATA_REGISTER) {
            return;
        }

        String fileId = (String) request.getPayload().get("FILE_ID");
        List<BizMemberImportDO> currentData = bizMemberImportRepository
                .findByOrgIdAndSourceId(request.getOrgId(), fileId);
        if (currentData.size() > 0) {
            for (BizMemberImportDO bizMemberImportDO : currentData) {
                bizMemberImportRepository.delete(bizMemberImportDO);
            }
            bizMemberImportRepository.flush();
        }

        SyncMemberConverter syncMemberConverter = new SyncMemberConverter(request.getOrgId(), fileId);

        List<String> memberIds = coreMemberService.getAllMemberIds(request.getOrgId());
        if (memberIds != null && memberIds.size() > 0) {
            for (String memberId : memberIds) {
                BizMemberImportDO memberImportDO = null;
                try {
                    CoreMember coreMember = coreMemberService.getOptimisticCoreMember(memberId);
                    CoreMemberExtension memberExtension = coreMemberService.getPessimisticCoreMemberExtension(memberId);
                    BizMember bizMember = BizMemberConverter.convert(coreMember, memberExtension);
                    memberImportDO = syncMemberConverter.convert(bizMember);

                    bizMemberImportRepository.saveAndFlush(memberImportDO);
                } catch (Exception e) {
                    if (memberImportDO != null) {
                        tryStoreFailedPayload(memberImportDO);
                    }
                }
            }
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