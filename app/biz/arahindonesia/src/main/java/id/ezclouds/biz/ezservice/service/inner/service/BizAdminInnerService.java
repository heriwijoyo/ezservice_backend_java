/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.inner.service;

import id.ezclouds.biz.ezservice.service.dataservice.AppImageGalleryService;
import id.ezclouds.biz.ezservice.service.dataservice.NewsInnerService;
import id.ezclouds.biz.ezservice.service.dataservice.VideoCardService;
import id.ezclouds.biz.ezservice.service.dataservice.model.AppImageGallery;
import id.ezclouds.biz.ezservice.service.dataservice.model.WebImageGallery;
import id.ezclouds.biz.ezservice.service.dataservice.request.AppImageGalleryRequest;
import id.ezclouds.biz.ezservice.service.dataservice.request.NewsCreateRequest;
import id.ezclouds.biz.ezservice.service.dataservice.request.VideoCardCreateRequest;
import id.ezclouds.biz.ezservice.service.result.PageResult;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminInnerService.java, v 0.1 2024‐02‐13 2:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAdminInnerService {

    @Autowired
    private AppImageGalleryService appImageGalleryService;

    @Autowired
    private NewsInnerService newsInnerService;

    @Autowired
    private VideoCardService videoCardService;

    public void createAppImageGallery(String orgId, String fileName, Map<String, String> extInfo) {
        AppImageGalleryRequest request = new AppImageGalleryRequest();
        request.setOrgId(orgId);
        request.setImageUrl(fileName);
        if (extInfo != null && !extInfo.isEmpty()) {
            request.setTitle(extInfo.get("TITLE"));
        }
        appImageGalleryService.createImageGallery(request);
    }

    public PageResult<WebImageGallery> getImageGalleryAll(String orgId, int pageNumber, int pageSize, String sortBy, String sort) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy, sort);
        return appImageGalleryService.getImageGalleryAll(orgId, pageRequest);
    }

    public void createNews(String orgId, String imageUrl, Map<String, String> extInfo) {
        NewsCreateRequest request = new NewsCreateRequest();
        request.setOrgId(orgId);
        request.setImageUrl(imageUrl);

        if (extInfo != null && !extInfo.isEmpty()) {
            request.setCategory(extInfo.get("CATEGORY"));
            request.setTitle(extInfo.get("TITLE"));
            request.setDescription(extInfo.get("DESCRIPTION"));
            request.setContent(extInfo.get("CONTENT"));
            request.setSource(extInfo.get("SOURCE"));
            request.setSourceUrl(extInfo.get("SOURCE_URL"));
            request.setPublishDate(extInfo.get("PUBLISH_DATE"));
        }
        newsInnerService.createNews(request);
    }

    public void createVideoCard(String orgId, String imageUrl, Map<String, String> extInfo) {
        VideoCardCreateRequest request = new VideoCardCreateRequest();
        request.setOrgId(orgId);
        request.setThumbnail(imageUrl);

        if (extInfo != null && !extInfo.isEmpty()) {
            request.setSection(extInfo.get("SECTION"));
            request.setSectionName(extInfo.get("SECTION_NAME"));
            request.setTitle(extInfo.get("TITLE"));
            request.setDescription(extInfo.get("DESCRIPTION"));
            request.setTargetType(extInfo.get("TARGET_TYPE"));
            request.setTargetUrl(extInfo.get("TARGET_URL"));
        }
        videoCardService.createVideoCard(request);
    }

    private PageRequest buildPageRequest(int page, int size, String sortBy, String sort) {
        if (StringUtil.isBlank(sortBy)) {
            return PageRequest.of(page - 1, size);
        }

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (StringUtil.equalsIgnoreCase("DESC", sort)) {
            sortDirection = Sort.Direction.DESC;
        }
        return PageRequest.of(page - 1, size, Sort.by(sortDirection, sortBy));
    }
}