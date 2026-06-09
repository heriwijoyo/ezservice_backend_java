/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.apibiz;

import id.ezclouds.biz.election.config.BizPublicUrlResolverImpl;
import id.ezclouds.biz.election.constant.AppConstant;
import id.ezclouds.biz.election.model.AppSetting;
import id.ezclouds.biz.election.model.HomeData;
import id.ezclouds.biz.election.model.VideoCard;
import id.ezclouds.biz.election.model.VideoSection;
import id.ezclouds.biz.election.model.news.BizSimpleNews;
import id.ezclouds.biz.election.service.app.AppConfigService;
import id.ezclouds.biz.election.service.app.AppImageGalleryService;
import id.ezclouds.biz.election.service.app.NewsInnerService;
import id.ezclouds.biz.election.service.app.VideoCardService;
import id.ezclouds.biz.election.service.app.model.AppImageGallery;
import id.ezclouds.biz.election.config.BizPublicUrlResolver;
import id.ezclouds.biz.election.converter.BizMemberConverter;
import id.ezclouds.biz.election.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.election.model.member.BizMember;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.common.util.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CoreMemberService coreMemberService;

    private BizPublicUrlResolver publicOrgResolver;

    public BizResult getAppSetting() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                AppSetting appSetting = new AppSetting();
                appSetting.setAppConfigMap(appConfigService.getAppConfigMap(getOrgId(), getOrgCode()));
                appSetting.setHomeData(composeHomeData(getOrgId()));

                try {
                    CoreAuthMemberSessionInfo session = authAppMemberSession();
                    CoreMember coreMember = coreMemberService
                            .getOptimisticCoreMember(session.getMemberId());
                    BizMember bizMember = BizMemberConverter.convert(coreMember, null);
                    BizAnnotationProcessor.annotatePublicConfig(bizMember, resolvePublicUrl(getOrgCode(), session.getMemberId()));

                    appSetting.getHomeData().setMemberAvatarUrl(bizMember.getAvatarUrl());
                } catch (Exception e) {}

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

    private BizPublicUrlResolver getPublicOrgResolver() {
        if (publicOrgResolver == null) {
            publicOrgResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, getOrgCode());
        }
        return publicOrgResolver;
    }

    private HomeData composeHomeData(String orgId) {
        HomeData homeData = new HomeData();
        homeData.setHighlightBanners(fetchHomeSlideGallery(orgId));
        homeData.setHighlightNews(fetchSimpleNews(orgId));
        homeData.setHomePosters(new ArrayList<>());
        homeData.setVideoSections(composeVideoSections(orgId));
        homeData.setMidBannerUrl(fetchMidBannerUrl(orgId));

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
                .getHighlightedNews(orgId)
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
            BizAnnotationProcessor.annotatePublicConfig(videoCard, getPublicOrgResolver());
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