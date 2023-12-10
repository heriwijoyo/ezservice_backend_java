/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.model.*;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSettingService.java, v 0.1 2023‐12‐09 12:53 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppSettingService {

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private ImageSlideService imageSlideService;

    @Autowired
    private NewsService newsService;

    public AppSetting getAppSetting() {
        String orgId = EzAppContextHolder.getOrganization().getOrgId();
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
        homeData.setHighlightBanners(fetchHomeImageSlide(orgId));
        homeData.setHighlightNews(fetchSimpleNews(orgId));
        homeData.setHomePosters(fetchHomePoster(orgId));

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
}