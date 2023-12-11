/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.api;

import id.ezclouds.biz.arahindonesia.model.ImageSlide;
import id.ezclouds.biz.arahindonesia.model.profile.CandidateBio;
import id.ezclouds.biz.arahindonesia.model.profile.CandidateProfile;
import id.ezclouds.biz.arahindonesia.model.profile.CandidateProfileItem;
import id.ezclouds.biz.arahindonesia.service.data.ImageSlideService;
import id.ezclouds.biz.arahindonesia.service.data.CandidateBioService;
import id.ezclouds.biz.arahindonesia.service.data.CandidateProfileItemService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateProfileService.java, v 0.1 2023‐12‐10 3:09 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CandidateProfileService {

    private static final String KEY_CONTACT_NUMBER = "contactNumber";
    private static final String KEY_VISION = "vision";
    private static final String KEY_MISSION = "mission";

    @Autowired
    private CandidateProfileItemService candidateProfileItemService;

    @Autowired
    private ImageSlideService imageSlideService;

    @Autowired
    private CandidateBioService candidateBioService;

    public CandidateProfile getCandidateProfile() {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        CandidateProfile profile = new CandidateProfile();

        setCandidateProfile(profile, orgId);

        return profile;
    }

    private void setCandidateProfile(CandidateProfile profile, String orgId) {
        List<CandidateProfileItem> profileItems = candidateProfileItemService
                .getCandidateProfileItems()
                .stream()
                .filter(candidateProfileItem -> orgId.equals(candidateProfileItem.getOrgId()))
                .collect(Collectors.toList());

        profileItems
                .forEach(candidateProfileItem -> {
                    if (KEY_CONTACT_NUMBER.equals(candidateProfileItem.getSection())) {
                        profile.setContactNumber(candidateProfileItem.getValue());
                    }

                    if (KEY_VISION.equals(candidateProfileItem.getSection())) {
                        profile.setVision(candidateProfileItem.getValue());
                    }

                    if (KEY_MISSION.equals(candidateProfileItem.getSection())) {
                        profile.setMission(candidateProfileItem.getValue());
                    }
                });

        List<ImageSlide> portfolioImages = imageSlideService
                .getPortfolioImage()
                .stream()
                .filter(imageSlide -> orgId.equals(imageSlide.getOrgId()))
                .collect(Collectors.toList());
        profile.setPortfolios(portfolioImages);

        List<CandidateBio> candidateBios = candidateBioService
                .getActiveCandidateBios()
                .stream()
                .filter(candidateBio -> orgId.equals(candidateBio.getOrgId()))
                .collect(Collectors.toList());
        profile.setCandidateBios(candidateBios);
    }
}