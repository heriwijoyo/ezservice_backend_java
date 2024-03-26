/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.inner.service;

import id.ezclouds.biz.ezservice.model.admin.BizOrganization;
import id.ezclouds.biz.ezservice.service.dataservice.AppImageGalleryService;
import id.ezclouds.biz.ezservice.service.dataservice.NewsInnerService;
import id.ezclouds.biz.ezservice.service.dataservice.VideoCardService;
import id.ezclouds.biz.ezservice.service.dataservice.model.WebImageGallery;
import id.ezclouds.biz.ezservice.service.dataservice.request.AppImageGalleryRequest;
import id.ezclouds.biz.ezservice.service.dataservice.request.NewsCreateRequest;
import id.ezclouds.biz.ezservice.service.dataservice.request.VideoCardCreateRequest;
import id.ezclouds.biz.ezservice.service.result.PageResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.repo.dataobject.EzCoreOrganizationDO;
import id.ezclouds.core.shared.service.CoreOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private CoreOrganizationService coreOrganizationService;

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

    public String updateImageGallery(String orgId, String itemId, String section, String value) {
        return appImageGalleryService.updateImageGallery(orgId, itemId, section, value);
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

    public PageResult<BizOrganization> getOrganizationAll(int pageNumber, int pageSize, String sortBy, String sort) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy, sort);
        Page<EzCoreOrganizationDO> findResult = coreOrganizationService.getOrganizationAll(pageRequest);
        List<BizOrganization> resultData = findResult
                .getContent()
                .stream()
                .map(model -> {
                    BizOrganization organization = new BizOrganization();
                    organization.setOrgId(model.getOrgId());
                    organization.setName(model.getName());
                    organization.setCode(model.getCode());
                    organization.setAddress(model.getAddress());
                    organization.setContactName(model.getContactName());
                    organization.setContactPhone(model.getContactPhone());
                    organization.setContactEmail(model.getContactEmail());
                    organization.setStatus(model.getStatus());
                    return organization;
                })
                .collect(Collectors.toList());

        PageResult<BizOrganization> pageResult = new PageResult<>();
        composePageResult(pageResult, findResult);
        pageResult.setData(resultData);
        return pageResult;
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

    private void composePageResult(PageResult pageResult, Page page) {
        pageResult.setPageNumber(page.getPageable().getPageNumber() + 1);
        pageResult.setPageSize(page.getPageable().getPageSize());
        pageResult.setNumberRecord(page.getNumberOfElements());
        pageResult.setTotalPage(page.getTotalPages());
        pageResult.setTotalRecord((int) page.getTotalElements());
        pageResult.setHasNext(page.hasNext());
        pageResult.setHasPrevious(page.hasPrevious());
    }
}