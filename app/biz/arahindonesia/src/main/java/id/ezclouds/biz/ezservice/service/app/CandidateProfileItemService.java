/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.model.profile.CandidateProfileItem;
import id.ezclouds.biz.ezservice.service.core.BizCacheKey;
import id.ezclouds.biz.ezservice.service.app.dataobject.CandidateProfileItemDO;
import id.ezclouds.biz.ezservice.service.app.repo.CandidateProfileItemRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateProfileItemService.java, v 0.1 2023‐12‐10 3:32 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CandidateProfileItemService {

    @Autowired
    private CandidateProfileItemRepository candidateProfileItemRepository;

    @Cacheable(value = BizCacheKey.CANDIDATE_PROFILE)
    public List<CandidateProfileItem> getCandidateProfileItems() {
        return candidateProfileItemRepository
                .findAll()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void storeProfileItem(String orgId, List<CandidateProfileItem> items) {
        for (CandidateProfileItem item : items) {
            CandidateProfileItemDO itemDO = candidateProfileItemRepository
                    .findByOrgIdAndSection(orgId, item.getSection());
            if (itemDO == null) {
                itemDO = new CandidateProfileItemDO();
                itemDO.setId(HashUtil.createHash(orgId, DateUtil.getCurrentFormattedDate()));
                itemDO.setOrgId(orgId);
                itemDO.setSection(item.getSection());
            }
            itemDO.setValue(item.getValue());

            candidateProfileItemRepository.saveAndFlush(itemDO);
        }
    }
}