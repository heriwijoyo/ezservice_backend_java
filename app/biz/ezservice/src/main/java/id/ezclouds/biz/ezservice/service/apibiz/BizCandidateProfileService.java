/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.config.BizPublicUrlResolverImpl;
import id.ezclouds.biz.ezservice.enums.BizProfileSection;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.model.profile.BizCandidateProfile;
import id.ezclouds.biz.ezservice.model.profile.CandidateBio;
import id.ezclouds.biz.ezservice.model.profile.CandidateProfileItem;
import id.ezclouds.biz.ezservice.model.profile.WebCandidateBio;
import id.ezclouds.biz.ezservice.service.app.AppImageGalleryService;
import id.ezclouds.biz.ezservice.service.app.CandidateBioService;
import id.ezclouds.biz.ezservice.service.app.CandidateProfileItemService;
import id.ezclouds.biz.ezservice.service.app.model.AppImageGallery;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateProfileService.java, v 0.1 2023‐12‐10 3:09 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizCandidateProfileService extends BizBaseService {

    @Autowired
    private CandidateProfileItemService candidateProfileItemService;

    @Autowired
    private AppImageGalleryService appImageGalleryService;

    @Autowired
    private CandidateBioService candidateBioService;

    public BizResult getCandidateProfile() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws EzErrorException {
                bizResult.setSuccess(true);
                bizResult.setObject(getAppCandidateProfile(getOrgId(), getOrgCode()));
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    private BizCandidateProfile<CandidateBio> getAppCandidateProfile(String orgId, String orgCode) {
        BizCandidateProfile<CandidateBio> profile = new BizCandidateProfile<>();
        setProfileItems(profile, orgId);
        setPortfolios(profile, orgId, orgCode);

        List<CandidateBio> candidateBios = candidateBioService
                .getActiveCandidateBios()
                .stream()
                .filter(candidateBio -> orgId.equals(candidateBio.getOrgId()))
                .collect(Collectors.toList());
        profile.setCandidateBios(candidateBios);
        return profile;
    }

    public BizCandidateProfile<WebCandidateBio> getWebCandidateProfile(String orgId, String orgCode) {
        BizCandidateProfile<WebCandidateBio> profile = new BizCandidateProfile<>();
        setProfileItems(profile, orgId);
        setPortfolios(profile, orgId, orgCode);
        profile.setCandidateBios(candidateBioService.getAllCandidateBios(orgId));
        return profile;
    }

    public void storeProfile(String orgId, Map<String, String> profileMap) {
        List<CandidateProfileItem> items = new ArrayList<>();

        for (Map.Entry<String, String> entry : profileMap.entrySet()) {
            BizProfileSection bizProfileSection = BizProfileSection.getByCode(entry.getKey());
            if (bizProfileSection != BizProfileSection.UNKNOWN) {
                CandidateProfileItem item = new CandidateProfileItem();
                item.setSection(entry.getKey());
                item.setValue(entry.getValue());
                items.add(item);
            }
        }

        candidateProfileItemService.storeProfileItem(orgId, items);
    }

    public void profileBioUpdate(String orgId, List<WebCandidateBio> bioData) {
        candidateBioService.restoreProfileBio(orgId, bioData);
    }

    private void setProfileItems(BizCandidateProfile profile, String orgId) {
        List<CandidateProfileItem> profileItems = candidateProfileItemService
                .getCandidateProfileItems()
                .stream()
                .filter(candidateProfileItem -> orgId.equals(candidateProfileItem.getOrgId()))
                .collect(Collectors.toList());

        profileItems
                .forEach(candidateProfileItem -> {
                    if (BizProfileSection.CONTACT_NUMBER.getCode().equals(candidateProfileItem.getSection())) {
                        profile.setContactNumber(candidateProfileItem.getValue());
                    }

                    if (BizProfileSection.VISION.getCode().equals(candidateProfileItem.getSection())) {
                        profile.setVision(candidateProfileItem.getValue());
                    }

                    if (BizProfileSection.MISSION.getCode().equals(candidateProfileItem.getSection())) {
                        profile.setMission(candidateProfileItem.getValue());
                    }
                });
    }

    private void setPortfolios(BizCandidateProfile profile, String orgId, String orgCode) {
        List<AppImageGallery> portfolios = appImageGalleryService.getAppGalleryPortfolioSlide(orgId);
        BizPublicUrlResolver resolver = new BizPublicUrlResolverImpl(appRootPublicUrl, orgCode);
        portfolios.forEach(gall -> {
            BizAnnotationProcessor.annotatePublicConfig(gall, resolver);
        });
        profile.setPortfolios(portfolios);
    }
}