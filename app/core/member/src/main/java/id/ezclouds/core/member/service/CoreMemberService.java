/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.core.member.dataobject.CoreMemberDO;
import id.ezclouds.core.member.dataobject.CoreMemberExtensionDO;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.repo.CoreMemberExtensionRepository;
import id.ezclouds.core.member.repo.CoreMemberRepository;
import id.ezclouds.core.member.util.CoreMemberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberService.java, v 0.1 2023‐12‐31 7:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberService {

    @Autowired
    private CoreMemberRepository coreMemberRepository;

    @Autowired
    private CoreMemberExtensionRepository coreMemberExtensionRepository;

    public void store(CoreMember coreMember, CoreMemberExtension coreMemberExtension) {
        CoreMemberDO coreMemberDO = CoreMemberConverter.convert(coreMember);
        coreMemberRepository.save(coreMemberDO);

        CoreMemberExtensionDO extensionDO = CoreMemberConverter.convert(coreMemberExtension);
        coreMemberExtensionRepository.save(extensionDO);
    }
}