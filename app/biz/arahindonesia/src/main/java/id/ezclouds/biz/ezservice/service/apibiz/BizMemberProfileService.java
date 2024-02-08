/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.model.profile.MemberProfile;
import id.ezclouds.biz.ezservice.service.dataservice.AppProfileService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberProfileService.java, v 0.1 2023‐12‐11 1:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberProfileService {

    @Autowired
    private AppProfileService appProfileService;

    public MemberProfile getMemberProfile() {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        MemberProfile profile = new MemberProfile();
        setAppProfile(profile, orgId);

        return profile;
    }

    private void setAppProfile(MemberProfile profile, String orgId) {
        appProfileService
                .getAllAppProfile()
                .stream()
                .filter(appProfileDO -> orgId.equals(appProfileDO.getOrgId()))
                .forEach(appProfileDO -> {
                    profile.getAppProfiles().put(appProfileDO.getProfileKey(), appProfileDO.getProfileValue());
                });
    }
}