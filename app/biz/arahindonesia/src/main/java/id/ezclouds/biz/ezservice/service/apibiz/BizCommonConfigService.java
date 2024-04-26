/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.config.BizPublicUrlResolverImpl;
import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.model.*;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.model.news.BizSimpleNews;
import id.ezclouds.biz.ezservice.service.dataservice.*;
import id.ezclouds.biz.ezservice.service.dataservice.model.AppImageGallery;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSettingService.java, v 0.1 2023‐12‐09 12:53 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizCommonConfigService extends BizBaseService {

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private AppImageGalleryService appImageGalleryService;

    @Autowired
    private NewsInnerService newsInnerService;

    @Autowired
    private VideoCardService videoCardService;

    @Autowired
    private BizCandidateProfileService bizCandidateProfileService;

    public BizResult getAppSetting() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws EzErrorException {
                AppSetting appSetting = new AppSetting();
                appSetting.setAppConfig(appConfigService.getAppConfig(getOrgId()));
                appSetting.setAppConfigMap(appConfigService.getAppConfigMap(getOrgId()));
                appSetting.setHomeData(composeHomeData(getOrgId()));
                bizResult.setSuccess(true);
                bizResult.setObject(appSetting);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizPublicUrlResolver resolvePublicUrl(String orgCode, String memberId) {
        return new BizPublicUrlResolverImpl(appRootPublicUrl, orgCode, memberId);
    }

    public BizPublicUrlResolver resolvePublicUrl(String orgCode) {
        return new BizPublicUrlResolverImpl(appRootPublicUrl, orgCode);
    }

    private HomeData composeHomeData(String orgId) {
        HomeData homeData = new HomeData();
        homeData.setPemiluDeadline("2024-02-14 00:00:00");
        homeData.setHighlightBanners(fetchHomeSlideGallery(orgId));
        homeData.setHighlightNews(fetchSimpleNews(orgId));
        homeData.setHomePosters(new ArrayList<>());
        homeData.setVideoSections(composeVideoSections(orgId));
        homeData.setMidBannerUrl(fetchMidBannerUrl(orgId));

        //support V1 compatibility
        int appVersionNo = EzAppContextHolder.getContext().getAppVersionNo();
        if (appVersionNo < AppConstant.APP_V2_START_VERSION_NO) {
            //TODO: compose candidate profile
            homeData.setCandidateProfile(
                    bizCandidateProfileService.getCandidateProfileOld()
            );
        }

        return homeData;
    }

    private List<AppImageGallery> fetchHomeSlideGallery(String orgId) {
        List<AppImageGallery> galleries = appImageGalleryService
                .getAppGalleryHomeSlide(orgId);

        String orgCode = EzAppContextHolder.getContext().getOrgCode();
        BizPublicUrlResolver resolver = resolvePublicUrl(orgCode);
        galleries.forEach(gall -> {
            BizAnnotationProcessor.annotatePublicConfig(gall, resolver);
        });

        return galleries;
    }

    private String fetchMidBannerUrl(String orgId) {
        AppImageGallery gallery = appImageGalleryService
                .getImageGalleryAllActive()
                .stream()
                .filter(appGallery -> orgId.equals(appGallery.getOrgId()) && appGallery.getFlagMidBanner() == 1)
                .findFirst()
                .orElse(null);

        if (gallery != null) {
            String orgCode = EzAppContextHolder.getContext().getOrgCode();
            BizPublicUrlResolver resolver = resolvePublicUrl(orgCode);
            BizAnnotationProcessor.annotatePublicConfig(gallery, resolver);

            return gallery.getImageUrl();
        }
        return null;
    }

    private List<BizSimpleNews> fetchSimpleNews(String orgId) {
        List<BizSimpleNews> news = newsInnerService
                .getHighlightedNews()
                .stream()
                .filter(simpleNews -> orgId.equals(simpleNews.getOrgId()))
                .limit(AppConstant.HIGHLIGHTED_NEWS_LIMIT)
                .collect(Collectors.toList());

        String orgCode = EzAppContextHolder.getContext().getOrgCode();
        BizPublicUrlResolver resolver = resolvePublicUrl(orgCode);
        news.forEach(newsItem -> {
            BizAnnotationProcessor.annotatePublicConfig(newsItem, resolver);
        });
        return news;
    }

    private List<VideoSection> composeVideoSections(String orgId) {
        List<VideoSection> videoSections = new ArrayList<>();

        List<VideoCard> allVideoCards = videoCardService
                .getAllVideoCards()
                .stream()
                .filter(videoCard -> orgId.equals(videoCard.getOrgId()))
                .collect(Collectors.toList());

        for (VideoCard videoCard : allVideoCards) {
            boolean isMapped = false;
            for (VideoSection videoSection : videoSections) {
                if (videoSection.getSectionName().equals(videoCard.getSectionName())) {
                    videoSection.getVideoCards().add(videoCard);
                    isMapped = true;
                }
            }

            if (!isMapped) {
                VideoSection newVideoSection = new VideoSection();
                newVideoSection.setSectionName(videoCard.getSectionName());
                newVideoSection.getVideoCards().add(videoCard);
                videoSections.add(newVideoSection);
            }
        }

        return videoSections;
    }
}