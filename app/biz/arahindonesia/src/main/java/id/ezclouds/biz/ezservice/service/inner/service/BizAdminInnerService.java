/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.inner.service;

import id.ezclouds.biz.ezservice.service.dataservice.AppImageGalleryService;
import id.ezclouds.biz.ezservice.service.dataservice.NewsInnerService;
import id.ezclouds.biz.ezservice.service.dataservice.request.AppImageGalleryRequest;
import id.ezclouds.biz.ezservice.service.dataservice.request.NewsCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void createAppImageGallery(String orgId, String fileName, Map<String, String> extInfo) {
        AppImageGalleryRequest request = new AppImageGalleryRequest();
        request.setOrgId(orgId);
        request.setImageUrl(fileName);
        if (extInfo != null && !extInfo.isEmpty()) {
            //TODO: compose other request information
        }
        appImageGalleryService.createImageGallery(request);
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
}