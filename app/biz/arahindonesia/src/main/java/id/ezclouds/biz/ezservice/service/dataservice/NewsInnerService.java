/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.model.news.BizSimpleNews;
import id.ezclouds.biz.ezservice.model.news.BizWebDetailNews;
import id.ezclouds.biz.ezservice.model.news.BizWebSimpleNews;
import id.ezclouds.biz.ezservice.service.core.BizCacheKey;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.NewsDO;
import id.ezclouds.biz.ezservice.service.dataservice.repo.NewsRepository;
import id.ezclouds.biz.ezservice.service.dataservice.request.NewsCreateRequest;
import id.ezclouds.biz.ezservice.service.result.PageResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Transactional
    public void updateNews(String orgId, BizWebDetailNews detailNews) {
        NewsDO newsDO = newsRepository.findByNewsIdAndOrgId(detailNews.getNewsId(), orgId);
        AssertUtil.notNull(newsDO, EzErrorCode.DATA_NOT_FOUND);
        if (StringUtil.isNotBlank(detailNews.getImageUrl())) {
            newsDO.setImageUrl(detailNews.getImageUrl());
        }
        newsDO.setTitle(detailNews.getTitle());
        newsDO.setDescription(detailNews.getDescription());
        newsDO.setPublishDate(detailNews.getPublishDate());
        newsDO.setCategory(detailNews.getCategory());
        newsDO.setSource(detailNews.getSource());
        newsDO.setSourceUrl(detailNews.getSourceUrl());
        newsDO.setContent(detailNews.getContent());
        newsRepository.saveAndFlush(newsDO);
    }

    @Cacheable(BizCacheKey.NEWS_HIGHLIGHT)
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

    public PageResult<BizWebSimpleNews> adminGetSimpleNews(String orgId, PageRequest pageRequest) {
        Page<NewsDO> findResult = newsRepository.findByOrgId(orgId, pageRequest);

        List<BizWebSimpleNews> resultData = findResult
                .getContent()
                .stream()
                .map(modelDO -> {
                    BizWebSimpleNews bizSimpleNews = new BizWebSimpleNews();
                    bizSimpleNews.setNewsId(modelDO.getNewsId());
                    bizSimpleNews.setImageUrl(modelDO.getImageUrl());
                    bizSimpleNews.setTitle(modelDO.getTitle());
                    bizSimpleNews.setDescription(modelDO.getDescription());
                    bizSimpleNews.setSource(modelDO.getSource());
                    bizSimpleNews.setSourceUrl(modelDO.getSourceUrl());
                    bizSimpleNews.setPublishDate(modelDO.getPublishDate());
                    bizSimpleNews.setStatus(modelDO.getStatus());
                    bizSimpleNews.setHighlight(modelDO.getHighlight());
                    return bizSimpleNews;
                })
                .collect(Collectors.toList());

        PageResult<BizWebSimpleNews> pageResult = new PageResult<>();
        pageResult.setPageNumber(findResult.getPageable().getPageNumber() + 1);
        pageResult.setPageSize(findResult.getPageable().getPageSize());
        pageResult.setNumberRecord(findResult.getNumberOfElements());
        pageResult.setTotalPage(findResult.getTotalPages());
        pageResult.setTotalRecord((int)findResult.getTotalElements());
        pageResult.setHasNext(findResult.hasNext());
        pageResult.setHasPrevious(findResult.hasPrevious());
        pageResult.setData(resultData);
        return pageResult;
    }

    public BizWebDetailNews adminGetNewsDetail(String orgId, String newsId) {
        NewsDO newsDO = newsRepository.findByNewsIdAndOrgId(newsId, orgId);
        AssertUtil.notNull(newsDO, EzErrorCode.DATA_NOT_FOUND);
        BizWebDetailNews detailNews = new BizWebDetailNews();
        detailNews.setNewsId(newsDO.getNewsId());
        detailNews.setTitle(newsDO.getTitle());
        detailNews.setDescription(newsDO.getDescription());
        detailNews.setCategory(newsDO.getCategory());
        detailNews.setContent(newsDO.getContent());
        detailNews.setImageUrl(newsDO.getImageUrl());
        detailNews.setPublishDate(newsDO.getPublishDate());
        detailNews.setSource(newsDO.getSource());
        detailNews.setSourceUrl(newsDO.getSourceUrl());
        return detailNews;
    }

    @Transactional
    public void adminNewsFlagSwitch(String orgId, String newsId, String section, int value) {
        NewsDO newsDO = newsRepository.findByNewsIdAndOrgId(newsId, orgId);
        AssertUtil.notNull(newsDO, EzErrorCode.DATA_NOT_FOUND);
        if ("highlight".equals(section)) {
            newsDO.setHighlight(value);
        }
        if ("status".equals(section)) {
            newsDO.setStatus(value);
        }
        newsRepository.saveAndFlush(newsDO);
    }
}