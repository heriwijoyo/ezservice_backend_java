/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.model.*;
import id.ezclouds.biz.arahindonesia.model.news.SimpleNews;
import id.ezclouds.biz.arahindonesia.service.NewsService;
import id.ezclouds.biz.arahindonesia.service.data.AppConfigService;
import id.ezclouds.biz.arahindonesia.service.data.ImageSlideService;
import id.ezclouds.biz.arahindonesia.service.data.VideoCardService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
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
public class BizAppSettingService {

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private ImageSlideService imageSlideService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private VideoCardService videoCardService;

    public AppSetting getAppSetting() {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        AppConfig appConfig = appConfigService
                .getAppConfigs()
                .stream()
                .filter(aConfig -> orgId.equals(aConfig.getOrgId()))
                .findFirst()
                .get();

        AppSetting appSetting = new AppSetting();
        appSetting.setAppConfig(appConfig);
        appSetting.setHomeData(composeHomeData(orgId));

        return appSetting;
    }

    private HomeData composeHomeData(String orgId) {
        HomeData homeData = new HomeData();
        homeData.setPemiluDeadline("2024-02-14 00:00:00");
        homeData.setHighlightBanners(fetchHomeImageSlide(orgId));
        homeData.setHighlightNews(fetchSimpleNews(orgId));
        homeData.setHomePosters(fetchHomePoster(orgId));
        homeData.setVideoSections(composeVideoSections(orgId));
        homeData.setMidBannerUrl(AppConstant.TMP_MID_BANNER_URL);

        return homeData;
    }

    private List<ImageSlide> fetchHomeImageSlide(String orgId) {
        return imageSlideService
                .getImageSlideHome()
                .stream()
                .filter(imageSlide -> orgId.equals(imageSlide.getOrgId()))
                .collect(Collectors.toList());
    }

    private List<ImageSlide> fetchHomePoster(String orgId) {
        return imageSlideService
                .getHomePosterImage()
                .stream()
                .filter(imageSlide -> orgId.equals(imageSlide.getOrgId()))
                .limit(1)
                .collect(Collectors.toList());
    }

    private List<SimpleNews> fetchSimpleNews(String orgId) {
        return newsService
                .getHighlightedNews()
                .stream()
                .filter(simpleNews -> orgId.equals(simpleNews.getOrgId()))
                .limit(AppConstant.HIGHLIGHTED_NEWS_LIMIT)
                .collect(Collectors.toList());
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