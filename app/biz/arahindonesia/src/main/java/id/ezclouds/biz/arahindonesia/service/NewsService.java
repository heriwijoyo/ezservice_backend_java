/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service;

import id.ezclouds.biz.arahindonesia.converter.ModelConverter;
import id.ezclouds.biz.arahindonesia.model.news.SimpleNews;
import id.ezclouds.common.dal.NewsRepository;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: NewsService.java, v 0.1 2023‐12‐10 11:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class NewsService {

    private static final int NEWS_LIMIT = 10;

    @Autowired
    private NewsRepository newsRepository;

    @Cacheable("highlighted_news")
    public List<SimpleNews> getHighlightedNews() {
        return newsRepository
                .findHighlightedNews()
                .stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<SimpleNews> getActiveListNews() {
        String orgId = EzAppContextHolder.getOrganization().getOrgId();
        return newsRepository
                .findActiveNews(orgId, NEWS_LIMIT)
                .stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }
}