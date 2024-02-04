/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.converter;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.model.*;
import id.ezclouds.biz.arahindonesia.model.authentication.AppMemberClient;
import id.ezclouds.biz.arahindonesia.model.member.BizSubOrganization;
import id.ezclouds.biz.arahindonesia.model.news.SimpleNews;
import id.ezclouds.biz.arahindonesia.model.profile.CandidateBio;
import id.ezclouds.biz.arahindonesia.model.profile.CandidateProfileItem;
import id.ezclouds.common.dal.dataobject.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizModelConverter.java, v 0.1 2023‐12‐10 12:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class BizModelConverter {

    public static AppConfig convert(AppConfigDO appConfigDO) {
        if (appConfigDO == null) {
            return null;
        }
        AppConfig appConfig = new AppConfig();
        appConfig.setOrgId(appConfigDO.getOrgId());
        appConfig.setAppName(appConfigDO.getAppName());
        appConfig.setVersionCode(appConfigDO.getVersionCode());
        appConfig.setVersionName(appConfigDO.getVersionName());
        appConfig.setSliderAnimationDuration(appConfigDO.getSliderAnimationDuration());
        appConfig.setMaxTpsNumber(appConfigDO.getMaxTpsNumber());

        AppUpdateInfo appUpdateInfo = new AppUpdateInfo();
        appUpdateInfo.setTitle(AppConstant.APP_UPDATE_TITLE);
        appUpdateInfo.setMessage(AppConstant.APP_UPDATE_MESSAGE.replace(AppConstant.APP_VERSION_NAME_TAG, appConfigDO.getVersionName()));
        appUpdateInfo.setNeedForceUpdate(appConfigDO.getNeedForceUpdate() == 1);
        appUpdateInfo.setUpdateUrl(appConfigDO.getUpdateUrl());

        appConfig.setAppUpdateInfo(appUpdateInfo);

        return appConfig;
    }

    public static BizSubOrganization convert(AppSubOrganizationDO subOrganizationDO) {
        if (subOrganizationDO == null) { return null; }
        BizSubOrganization subOrganization = new BizSubOrganization();
        subOrganization.setSubOrgId(subOrganizationDO.getSubOrgId());
        subOrganization.setName(subOrganizationDO.getName());
        return subOrganization;
    }

    public static ImageSlide convert(ImageSlideDO slideDO) {
        if (slideDO == null) {
            return null;
        }
        ImageSlide imageSlide = new ImageSlide();
        imageSlide.setOrgId(slideDO.getOrgId());
        imageSlide.setTitle(slideDO.getTitle());
        imageSlide.setImageUrl(slideDO.getImageUrl());
        imageSlide.setTargetType(slideDO.getTargetType());
        imageSlide.setTargetUrl(slideDO.getTargetUrl());

        return imageSlide;
    }

    public static SimpleNews convert(NewsDO newsDO) {
        if (newsDO == null) {
            return null;
        }
        SimpleNews simpleNews = new SimpleNews();
        simpleNews.setNewsId(newsDO.getNewsId());
        simpleNews.setOrgId(newsDO.getOrgId());
        simpleNews.setTitle(newsDO.getTitle());
        simpleNews.setThumbnail(newsDO.getThumbnail());
        simpleNews.setDescription(newsDO.getDescription());
        return simpleNews;
    }

    public static VideoCard convert(VideoCardDO cardDO) {
        if (cardDO == null) {
            return null;
        }
        VideoCard videoCard = new VideoCard();
        videoCard.setOrgId(cardDO.getOrgId());
        videoCard.setSection(cardDO.getSection());
        videoCard.setSectionName(cardDO.getSectionName());
        videoCard.setTargetType(cardDO.getTargetType());
        videoCard.setTargetUrl(cardDO.getTargetUrl());
        videoCard.setThumbnail(cardDO.getThumbnail());
        return videoCard;
    }

    public static CandidateProfileItem convert(CandidateProfileItemDO itemDO) {
        if (itemDO == null) {
            return null;
        }
        CandidateProfileItem item = new CandidateProfileItem();
        item.setOrgId(itemDO.getOrgId());
        item.setSection(itemDO.getSection());
        item.setValue(itemDO.getValue());
        return item;
    }

    public static CandidateBio convert(CandidateBioDO bioDO) {
        if (bioDO == null) {
            return null;
        }
        CandidateBio candidateBio = new CandidateBio();
        candidateBio.setOrgId(bioDO.getOrgId());
        candidateBio.setLabel(bioDO.getLabel());
        candidateBio.setValue(bioDO.getValue());
        return candidateBio;
    }
}