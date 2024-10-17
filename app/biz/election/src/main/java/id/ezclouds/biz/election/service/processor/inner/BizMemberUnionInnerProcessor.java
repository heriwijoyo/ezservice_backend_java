/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.processor.inner;

import id.ezclouds.biz.election.service.core.dataobject.BizMemberUnionDO;
import id.ezclouds.biz.election.service.core.repo.BizMemberUnionDuplicateRepository;
import id.ezclouds.biz.election.service.core.repo.BizMemberUnionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberUnionInnerProcessor.java, v 0.1 2024‐07‐24 4:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberUnionInnerProcessor {

    @Autowired
    private BizMemberUnionRepository bizMemberUnionRepository;

    @Autowired
    private BizMemberUnionDuplicateRepository bizMemberUnionDuplicateRepository;

    @Transactional
    public long deleteAllMemberUnion(String orgId) {
        return bizMemberUnionRepository.deleteByOrgId(orgId);
    }

    @Transactional
    public long deleteAllMemberUnionDuplicate(String orgId) {
        return bizMemberUnionDuplicateRepository.deleteByOrgId(orgId);
    }

    @Transactional
    public void storeMemberUnion(BizMemberUnionDO memberUnionDO) {
        bizMemberUnionRepository.saveAndFlush(memberUnionDO);
    }

    @Transactional
    public void storeMemberUnionDuplicate(BizMemberUnionDO memberUnionDO) {
        bizMemberUnionDuplicateRepository.saveAndFlush(memberUnionDO);
    }
}