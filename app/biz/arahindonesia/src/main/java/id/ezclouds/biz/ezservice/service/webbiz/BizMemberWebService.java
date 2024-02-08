/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.webbiz;

import id.ezclouds.biz.ezservice.constant.WebLoadImageScene;
import id.ezclouds.biz.ezservice.service.dataservice.BizOrganizationService;
import id.ezclouds.biz.ezservice.service.request.BizImageLoadRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.member.MemberFileInfo;
import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.service.CoreFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
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


    public BizResult bizLoadCommonImage(BizImageLoadRequest request) {
        BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.WebHandler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(request.getMemberId(), EzErrorCode.MEDIA_NOT_FOUND);
                CoreOrganization organization = bizOrganizationService
                        .getOrganizationByCode(request.getOrgCode());
                AssertUtil.notNull(organization, EzErrorCode.MEDIA_NOT_FOUND);
                AssertUtil.notBlank(organization.getOrgId(), EzErrorCode.MEDIA_NOT_FOUND);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreOrganization organization = bizOrganizationService
                        .getOrganizationByCode(request.getOrgCode());
                MemberFileInfo fileInfo = coreFileService
                        .resolveMemberFileInfo(organization.getOrgId(), request.getMemberId());

                Path imagePath;
                WebLoadImageScene scene = WebLoadImageScene.getByCode(request.getScene());
                switch (scene) {
                    case AVATAR:
                        imagePath = fileInfo.getAvatarPath(request.getFileName());
                        break;

                    default:
                        throw new EzErrorException(EzErrorCode.MEDIA_NOT_FOUND);
                }

                AssertUtil.isTrue(Files.exists(imagePath), EzErrorCode.MEDIA_NOT_FOUND);

                bizResult.setSuccess(true);
                bizResult.setObject(imagePath);
            }
        });

        return bizResult;
    }
}