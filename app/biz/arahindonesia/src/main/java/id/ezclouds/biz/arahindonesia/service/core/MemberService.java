/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.core;

import id.ezclouds.biz.arahindonesia.converter.MemberConverter;
import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.common.dal.repo.member.AppMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberService.java, v 0.1 2023‐12‐11 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class MemberService {

    @Autowired
    private AppMemberRepository appMemberRepository;

    public MemberBase getMemberById(String memberId, String orgId) {
        return appMemberRepository
                .findByMemberIdAndOrgId(memberId, orgId)
                .stream()
                .findFirst()
                .map(MemberConverter::convert)
                .orElse(null);
    }
}