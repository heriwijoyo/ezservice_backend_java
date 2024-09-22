/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app;

import id.ezclouds.biz.election.model.profile.CandidateBio;
import id.ezclouds.biz.election.service.app.dataobject.CandidateBioDO;
import id.ezclouds.biz.election.service.app.repo.CandidateBioRepository;
import id.ezclouds.biz.election.converter.BizModelConverter;
import id.ezclouds.biz.election.model.profile.WebCandidateBio;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<CandidateBio> getActiveCandidateBios() {
        return candidateBioRepository
                .getActiveCandidateBios()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<WebCandidateBio> getAllCandidateBios(String orgId) {
        return candidateBioRepository
                .findByOrgId(orgId)
                .stream()
                .map(BizModelConverter::convertWeb)
                .collect(Collectors.toList());
    }

    @Transactional
    public void restoreProfileBio(String orgId, List<WebCandidateBio> bioData) {
        List<CandidateBioDO> existBio = candidateBioRepository.findByOrgId(orgId);
        for (CandidateBioDO bioDO: existBio) {
            candidateBioRepository.delete(bioDO);
            candidateBioRepository.flush();
        }

        String currentTime = DateUtil.getCurrentFormattedDate();
        int i = 0;
        for (WebCandidateBio webCandidateBio : bioData) {
            CandidateBioDO newBioDO = new CandidateBioDO();
            newBioDO.setId(HashUtil.createHash(currentTime, String.valueOf(i)));
            newBioDO.setOrgId(orgId);
            newBioDO.setLabel(webCandidateBio.getLabel());
            newBioDO.setValue(webCandidateBio.getValue());
            newBioDO.setSort(webCandidateBio.getSort());
            newBioDO.setStatus(webCandidateBio.getStatus());
            i++;

            candidateBioRepository.saveAndFlush(newBioDO);
        }
    }
}