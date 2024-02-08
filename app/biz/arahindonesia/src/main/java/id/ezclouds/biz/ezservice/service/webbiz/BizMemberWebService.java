/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.webbiz;

import id.ezclouds.biz.ezservice.service.dataservice.BizOrganizationService;
import id.ezclouds.biz.ezservice.service.request.BizImageLoadRequest;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.shared.member.MemberFileInfo;
import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.service.CoreFileService;
import id.ezclouds.core.shared.service.CoreOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberWebService.java, v 0.1 2024‐02‐08 8:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberWebService {

    @Autowired
    private BizOrganizationService bizOrganizationService;

    @Autowired
    private CoreFileService coreFileService;


    public Path bizLoadCommonImage(BizImageLoadRequest request) {
        AssertUtil.notBlank(request.getMemberId(), EzErrorCode.MEDIA_NOT_FOUND);
        CoreOrganization organization = bizOrganizationService
                .getOrganizationByCode(request.getOrgCode());
        AssertUtil.notNull(organization, EzErrorCode.MEDIA_NOT_FOUND);
        AssertUtil.notBlank(organization.getOrgId(), EzErrorCode.MEDIA_NOT_FOUND);

        MemberFileInfo fileInfo = coreFileService
                .resolveMemberFileInfo(organization.getOrgId(), request.getMemberId());

        return fileInfo.getAvatarPath(request.getFileName());
    }
}