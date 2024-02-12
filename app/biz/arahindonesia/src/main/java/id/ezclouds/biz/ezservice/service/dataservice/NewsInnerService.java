/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.model.news.BizSimpleNews;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.NewsDO;
import id.ezclouds.biz.ezservice.service.dataservice.repo.NewsRepository;
import id.ezclouds.biz.ezservice.service.dataservice.request.NewsCreateRequest;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: NewsInnerService.java, v 0.1 2023‐12‐10 11:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class NewsInnerService {

    private static final int NEWS_LIMIT = 10;

    @Autowired
    private NewsRepository newsRepository;

    @Transactional
    public void createNews(NewsCreateRequest request) {
        NewsDO newsDO = new NewsDO();
        newsDO.setNewsId(HashUtil.createHash(request.getOrgId(), DateUtil.getCurrentFormattedDate()));
        newsDO.setOrgId(request.getOrgId());
        newsDO.setCategory(request.getCategory());
        newsDO.setTitle(request.getTitle());
        newsDO.setImageUrl(request.getImageUrl());
        newsDO.setDescription(request.getDescription());
        newsDO.setContent(request.getContent());
        newsDO.setSource(request.getSource());
        newsDO.setSourceUrl(request.getSourceUrl());
        newsDO.setPublishDate(request.getPublishDate());
        newsDO.setStatus(request.getStatus());
        newsDO.setHighlight(request.getHighlight());
        newsRepository.saveAndFlush(newsDO);
    }

    @Cacheable("newsHighlighted")
    public List<BizSimpleNews> getHighlightedNews() {
        return newsRepository
                .findHighlightedNews()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<BizSimpleNews> getActiveListNews() {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        return newsRepository
                .findActiveNews(orgId, NEWS_LIMIT)
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }
}