/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.dataservice;

import id.ezclouds.biz.arahindonesia.service.dataservice.model.AppMemberFlag;
import id.ezclouds.biz.arahindonesia.service.dataservice.repo.AppMemberFlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMemberFlagService.java, v 0.1 2024‐02‐04 6:56 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppMemberFlagService {

    @Autowired
    private AppMemberFlagRepository appMemberFlagRepository;

    public Map<String, String> getAppMemberFlag(String orgId, String memberId) {
        Map<String, String> memberFlagMap = new HashMap<>();
        List<AppMemberFlag> appMemberFlags = appMemberFlagRepository
                .getActiveMemberFlags(orgId, memberId)
                .stream()
                .map(flagDO -> new AppMemberFlag(flagDO.getFlagCode(), flagDO.getFlagValue()))
                .collect(Collectors.toList());

        appMemberFlags
                .forEach(memberFlag -> {
                    memberFlagMap.put(memberFlag.getFlagCode(), memberFlag.getFlagValue());
                });

        return memberFlagMap;
    }
}