/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.data;

import id.ezclouds.biz.arahindonesia.converter.ModelConverter;
import id.ezclouds.biz.arahindonesia.model.profile.CandidateProfileItem;
import id.ezclouds.common.dal.profile.CandidateProfileItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Cacheable("candidate_profile_item")
    public List<CandidateProfileItem> getCandidateProfileItems() {
        return candidateProfileItemRepository
                .findAll()
                .stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }
}