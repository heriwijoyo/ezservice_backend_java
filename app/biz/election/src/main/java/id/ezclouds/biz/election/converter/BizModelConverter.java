/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.converter;

import id.ezclouds.biz.election.model.BizWhatsappLog;
import id.ezclouds.biz.election.model.VideoCard;
import id.ezclouds.biz.election.model.news.BizSimpleNews;
import id.ezclouds.biz.election.model.profile.CandidateBio;
import id.ezclouds.biz.election.model.profile.CandidateProfileItem;
import id.ezclouds.biz.election.service.app.dataobject.CandidateBioDO;
import id.ezclouds.biz.election.service.app.dataobject.CandidateProfileItemDO;
import id.ezclouds.biz.election.service.app.dataobject.NewsDO;
import id.ezclouds.biz.election.service.app.dataobject.VideoCardDO;
import id.ezclouds.biz.election.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.election.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.biz.election.model.profile.WebCandidateBio;
import id.ezclouds.common.model.integration.WhatsappLog;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizModelConverter.java, v 0.1 2023‐12‐10 12:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class BizModelConverter {

    public static BizSubOrganization convert(BizSubOrganizationDO subOrganizationDO) {
        if (subOrganizationDO == null) { return null; }
        BizSubOrganization subOrganization = new BizSubOrganization();
        subOrganization.setOrgId(subOrganizationDO.getOrgId());
        subOrganization.setSubOrgId(subOrganizationDO.getSubOrgId());
        subOrganization.setName(subOrganizationDO.getName());
        subOrganization.setAddress(subOrganizationDO.getAddress());
        subOrganization.setCreatedTime(subOrganizationDO.getCreatedTime());
        subOrganization.setStatus(subOrganizationDO.getStatus());
        return subOrganization;
    }

    public static BizSimpleNews convert(NewsDO newsDO) {
        if (newsDO == null) {
            return null;
        }
        BizSimpleNews bizSimpleNews = new BizSimpleNews();
        bizSimpleNews.setNewsId(newsDO.getNewsId());
        bizSimpleNews.setOrgId(newsDO.getOrgId());
        bizSimpleNews.setTitle(newsDO.getTitle());
        bizSimpleNews.setImageUrl(newsDO.getImageUrl());
        bizSimpleNews.setDescription(newsDO.getDescription());
        return bizSimpleNews;
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

    public static WebCandidateBio convertWeb(CandidateBioDO bioDO) {
        if (bioDO == null) {
            return null;
        }
        WebCandidateBio candidateBio = new WebCandidateBio();
        candidateBio.setOrgId(bioDO.getOrgId());
        candidateBio.setLabel(bioDO.getLabel());
        candidateBio.setValue(bioDO.getValue());
        candidateBio.setSort(bioDO.getSort());
        candidateBio.setStatus(bioDO.getStatus());
        return candidateBio;
    }

    public static BizWhatsappLog convert(WhatsappLog whatsappLog) {
        if (whatsappLog == null) { return null; }
        BizWhatsappLog bizWhatsappLog = new BizWhatsappLog();
        bizWhatsappLog.setId(whatsappLog.getId());
        bizWhatsappLog.setOrgId(whatsappLog.getOrgId());
        bizWhatsappLog.setPhone(whatsappLog.getPhone());
        bizWhatsappLog.setMessage(whatsappLog.getMessage());
        bizWhatsappLog.setCreatedTime(whatsappLog.getCreatedTime());
        bizWhatsappLog.setStatus(whatsappLog.getStatus());
        bizWhatsappLog.setResponseTime(whatsappLog.getResponseTime());
        bizWhatsappLog.setResponseDetail(whatsappLog.getResponse());
        return bizWhatsappLog;
    }
}