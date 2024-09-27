/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreBizSequenceService;
import id.ezclouds.common.facade.dal.core.CoreBizSequenceDAO;
import id.ezclouds.common.model.core.CoreBizSeqScene;
import id.ezclouds.common.model.core.CoreBizSequence;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.shared.util.CoreSeqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreBizSequenceService.java, v 0.1 2024‐09‐28 1:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreBizSequenceService implements CoreBizSequenceService {

    @Autowired
    private CoreBizSequenceDAO coreBizSequenceDAO;

    @Override
    public String generateSequence(String orgId, CoreBizSeqScene bizSeqScene) {
        CoreBizSequence bizSequence = coreBizSequenceDAO
                .lockBizSequence(orgId, bizSeqScene);
        if (bizSequence == null) {
            bizSequence = new CoreBizSequence();
            bizSequence.setSeqBizKey(HashUtil.createHash(orgId, bizSeqScene.getCode()));
            bizSequence.setOrgId(orgId);
            bizSequence.setSeqBizKey(bizSeqScene.getCode());
            bizSequence.setSequence(0);
        }

        int updateSeq = bizSequence.getSequence() + 1;
        String seqCode = CoreSeqUtil.composeSeqCode(updateSeq, bizSeqScene.seqLength());

        bizSequence.setSequence(updateSeq);
        bizSequence.setModifiedTime(DateUtil.getCurrentFormattedDateMillis());
        coreBizSequenceDAO.store(bizSequence);

        return seqCode;
    }
}