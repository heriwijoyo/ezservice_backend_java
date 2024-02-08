/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.model.profile.CandidateBio;
import id.ezclouds.common.dal.repo.CandidateBioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateBioService.java, v 0.1 2023‐12‐10 3:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CandidateBioService {

    @Autowired
    private CandidateBioRepository candidateBioRepository;

    @Cacheable("candidate_bios")
    public List<CandidateBio> getActiveCandidateBios() {
        return candidateBioRepository
                .getActiveCandidateBios()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }
}