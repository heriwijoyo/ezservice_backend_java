/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app;

import id.ezclouds.biz.election.model.news.BizWebDetailNews;
import id.ezclouds.biz.election.service.app.dataobject.NewsDO;
import id.ezclouds.biz.election.service.app.repo.NewsRepository;
import id.ezclouds.biz.election.service.request.BizPageRequest;
import id.ezclouds.biz.election.converter.BizModelConverter;
import id.ezclouds.biz.election.model.news.BizNewsDetail;
import id.ezclouds.biz.election.model.news.BizSimpleNews;
import id.ezclouds.biz.election.model.news.BizWebSimpleNews;
import id.ezclouds.biz.election.service.app.request.NewsCreateRequest;
import id.ezclouds.common.model.result.BizPageInfo;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.biz.election.util.PageRequestUtil;
import id.ezclouds.core.shared.util.PageResultUtil;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.common.model.core.CoreOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: NewsInnerService.java, v 0.1 2023‐12‐10 11:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class NewsInnerService {

    private static final int NEWS_HIGHLIGHT_LIMIT = 3;
    private static final int NEWS_LIMIT = 10;
    private static final int STATUS_ACTIVE = 1;

    @Autowired
    private BizOrganizationService bizOrganizationService;

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

    public List<BizSimpleNews> getHighlightedNews(String orgId) {
        return getHighlightNewsAllOrg()
                .stream()
                .filter(news -> orgId.equals(news.getOrgId()))
                .collect(Collectors.toList());
    }

    public List<BizSimpleNews> getHighlightNewsAllOrg() {
        List<CoreOrganization> organizations = bizOrganizationService
                .getActiveOrganizations();

        List<NewsDO> highlightedNews = new ArrayList<>();
        for (CoreOrganization organization : organizations) {
            List<NewsDO> orgHighlightNews = newsRepository
                    .findActiveNews(organization.getOrgId(), NEWS_HIGHLIGHT_LIMIT);
            highlightedNews.addAll(orgHighlightNews);
        }

        return highlightedNews
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    public BizPageInfo<BizSimpleNews> getNewsPage(String orgId, BizPageRequest request) {
        request.setSortBy("publishDate");
        request.setSort("DESC");
        PageRequest pageRequest = PageRequestUtil.composePageRequest(request);

        Page<NewsDO> pageResult = newsRepository
                .findByOrgIdAndStatus(orgId, STATUS_ACTIVE, pageRequest);
        List<BizSimpleNews> bizData = new ArrayList<>();
        pageResult.getContent().forEach(modelDO -> {
            bizData.add(BizModelConverter.convert(modelDO));
        });

        BizPageInfo<BizSimpleNews> bizPageInfo = PageResultUtil.composePageInfo(pageResult);
        bizPageInfo.setBizData(bizData);
        return bizPageInfo;
    }

    public List<BizSimpleNews> getActiveListNews() {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        return newsRepository
                .findActiveNews(orgId, NEWS_LIMIT)
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    public BizNewsDetail getNewsDetail(String newsId) {
        NewsDO newsDO = newsRepository
                .findById(newsId)
                .orElse(null);
        AssertUtil.notNull(newsDO, EzErrorCode.DATA_NOT_FOUND);
        BizNewsDetail detail = new BizNewsDetail();
        detail.setTitle(newsDO.getTitle());
        detail.setImageUrl(newsDO.getImageUrl());
        detail.setContent(newsDO.getContent());
        detail.setPublishDate(newsDO.getPublishDate());
        detail.setSource(newsDO.getSource());
        detail.setSourceUrl(newsDO.getSourceUrl());
        return detail;
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