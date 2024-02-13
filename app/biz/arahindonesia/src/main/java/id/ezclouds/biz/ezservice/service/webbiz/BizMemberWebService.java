/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.webbiz;

import id.ezclouds.biz.ezservice.constant.ImageRestriction;
import id.ezclouds.biz.ezservice.constant.WebLoadImageScene;
import id.ezclouds.biz.ezservice.service.dataservice.BizOrganizationService;
import id.ezclouds.biz.ezservice.service.request.BizImageLoadRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.file.PrivateFileResolver;
import id.ezclouds.core.shared.member.PublicFileInfo;
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
                AssertUtil.isNotTrue(request.getScene() == WebLoadImageScene.UNKNOWN, EzErrorCode.MEDIA_NOT_FOUND);
                AssertUtil.notBlank(request.getOrgCode(), EzErrorCode.MEDIA_NOT_FOUND);

                CoreOrganization organization = bizOrganizationService
                        .getOrganizationByCode(request.getOrgCode());
                AssertUtil.notNull(organization, EzErrorCode.MEDIA_NOT_FOUND);
                AssertUtil.notBlank(organization.getOrgId(), EzErrorCode.MEDIA_NOT_FOUND);

                if (request.getScene().getRestriction() == ImageRestriction.PRIVATE) {
                    AssertUtil.notBlank(request.getMemberId(), EzErrorCode.MEDIA_NOT_FOUND);
                }
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreOrganization organization = bizOrganizationService
                        .getOrganizationByCode(request.getOrgCode());

                Path imagePath;
                if (request.getScene().getRestriction() == ImageRestriction.PRIVATE) {
                    PrivateFileResolver fileInfo = coreFileService
                            .resolveMemberFileInfo(organization.getOrgId(), request.getMemberId());

                    switch (request.getScene()) {
                        case AVATAR:
                            imagePath = fileInfo.getAvatarPath(request.getFileName());
                            break;

                        case ID_CARD:
                            imagePath = fileInfo.getIdCardPath(request.getFileName());
                            break;

                        case FAMILY_CARD:
                            imagePath = fileInfo.getFamilyCardPath(request.getFileName());
                            break;

                        default:
                            throw new EzErrorException(EzErrorCode.MEDIA_NOT_FOUND);
                    }

                } else if (request.getScene().getRestriction() == ImageRestriction.PUBLIC){
                    PublicFileInfo fileInfo = coreFileService
                            .resolvePublicFileInfo(organization.getOrgId());

                    switch (request.getScene()) {
                        case PUBLIC_APP_GALLERY:
                            imagePath = fileInfo.getAppGalleryPath(request.getFileName());
                            break;
                        case PUBLIC_NEWS_GALLERY:
                            imagePath = fileInfo.getNewsGalleryPath(request.getFileName());
                            break;
                        case PUBLIC_EVENT_GALLERY:
                            imagePath = fileInfo.getEventGalleryPath(request.getFileName());
                            break;

                        default:
                            throw new EzErrorException(EzErrorCode.MEDIA_NOT_FOUND);
                    }

                } else {
                    imagePath = null;
                }

                AssertUtil.isTrue(Files.exists(imagePath), EzErrorCode.MEDIA_NOT_FOUND);

                bizResult.setSuccess(true);
                bizResult.setObject(imagePath);
            }
        });

        return bizResult;
    }
}