/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app;

import id.ezclouds.biz.election.service.app.dataobject.AppReportGalleryDO;
import id.ezclouds.biz.election.service.app.repo.AppReportGalleryRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppReportService.java, v 0.1 2024‐04‐23 11:16 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppReportService {

    @Autowired
    private AppReportGalleryRepository appReportGalleryRepository;

    public void validateExtendInfo(Map<String, String> extInfo, String... extKeys) throws EzErrorException {
        AssertUtil.notNull(extInfo, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isNotTrue(extInfo.isEmpty(), EzErrorCode.ILLEGAL_PARAM);
        for (String extKey : extKeys) {
            AssertUtil.notBlank(extInfo.get(extKey), EzErrorCode.ILLEGAL_PARAM);
        }
    }

    @Transactional
    public void storeAppReport(Map<String, String> extInfo) {
        String currentTime = DateUtil.getCurrentFormattedDate();
        String orgId = extInfo.get("ORG_ID");
        String memberId = extInfo.get("MEMBER_ID");

        AppReportGalleryDO appReportGalleryDO = new AppReportGalleryDO();
        appReportGalleryDO.setId(HashUtil.createHash(orgId, memberId, currentTime));
        appReportGalleryDO.setOrgId(orgId);
        appReportGalleryDO.setMemberId(memberId);
        appReportGalleryDO.setCaption(extInfo.get("CAPTION"));
        appReportGalleryDO.setDescription(extInfo.get("DESCRIPTION"));
        appReportGalleryDO.setFileType(extInfo.get("FILE_TYPE"));
        appReportGalleryDO.setFileUrl(extInfo.get("FILE_URL"));
        appReportGalleryDO.setCreatedTime(currentTime);
        appReportGalleryRepository.saveAndFlush(appReportGalleryDO);
    }
}