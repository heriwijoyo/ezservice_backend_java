/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.api;

import id.ezclouds.biz.arahindonesia.model.profile.CandidateProfile;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateProfileService.java, v 0.1 2023‐12‐10 3:09 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CandidateProfileService {

    public CandidateProfile getCandidateProfile() {
        CandidateProfile profile = new CandidateProfile();
        profile.setContactNumber("123");
        profile.setVision("visi");
        profile.setMission("misi");

        return profile;
    }
}