/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app;

import id.ezclouds.biz.election.service.app.model.AppImageGallery;
import id.ezclouds.biz.election.service.app.model.WebImageGallery;
import id.ezclouds.biz.election.service.app.dataobject.AppImageGalleryDO;
import id.ezclouds.biz.election.service.app.repo.AppImageGalleryRepository;
import id.ezclouds.biz.election.service.app.request.AppImageGalleryRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppImageGalleryService.java, v 0.1 2024‐02‐12 10:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppImageGalleryService {

    @Autowired
    private AppImageGalleryRepository appImageGalleryRepository;

    @Transactional
    public void createImageGallery(AppImageGalleryRequest request) {
        AppImageGalleryDO galleryDO = new AppImageGalleryDO();
        String currentTime = DateUtil.getCurrentFormattedDate();
        galleryDO.setId(HashUtil.createHash(request.getOrgId(), currentTime));
        galleryDO.setOrgId(request.getOrgId());
        galleryDO.setTitle(request.getTitle());
        galleryDO.setImageUrl(request.getImageUrl());
        galleryDO.setTargetType(request.getTargetType());
        galleryDO.setTargetUrl(request.getTargetUrl());
        galleryDO.setFlagHomeSlide(request.getFlagHomeSlide());
        galleryDO.setFlagPortfolioSlide(request.getFlagPortfolioSlide());
        galleryDO.setCreatedTime(currentTime);
        galleryDO.setSorting(request.getSorting());
        galleryDO.setStatus(request.getStatus());

        appImageGalleryRepository.saveAndFlush(galleryDO);
    }

    public List<AppImageGallery> getAppGalleryHomeSlide(String orgId) {
        return getImageGalleryAllActive()
                .stream()
                .filter(gallery -> orgId.equals(gallery.getOrgId()) && gallery.getFlagHomeSlide() == 1)
                .collect(Collectors.toList());
    }

    public List<AppImageGallery> getAppGalleryPortfolioSlide(String orgId) {
        return getImageGalleryAllActive()
                .stream()
                .filter(gallery -> orgId.equals(gallery.getOrgId()) && gallery.getFlagPortfolioSlide() == 1)
                .collect(Collectors.toList());
    }

    public List<AppImageGallery> getImageGalleryAllActive() {
        return appImageGalleryRepository
                .findAllActive()
                .stream()
                .map(modelDO -> {
                    AppImageGallery gallery = new AppImageGallery();
                    gallery.setOrgId(modelDO.getOrgId());
                    gallery.setTitle(modelDO.getTitle());
                    gallery.setImageUrl(modelDO.getImageUrl());
                    gallery.setTargetType(modelDO.getTargetType());
                    gallery.setTargetUrl(modelDO.getTargetUrl());
                    gallery.setFlagHomeSlide(modelDO.getFlagHomeSlide());
                    gallery.setFlagPortfolioSlide(modelDO.getFlagPortfolioSlide());
                    gallery.setFlagMidBanner(modelDO.getFlagMidBanner());
                    gallery.setSorting(modelDO.getSorting());
                    return gallery;
                })
                .collect(Collectors.toList());
    }

    public PageResult<WebImageGallery> getImageGalleryAll(String orgId, PageRequest pageRequest) {
        Page<AppImageGalleryDO> findResult = appImageGalleryRepository.findByOrgId(orgId, pageRequest);

        List<WebImageGallery> resultData = findResult
                .getContent()
                .stream()
                .map(modelDO -> {
                    WebImageGallery gallery = new WebImageGallery();
                    gallery.setId(modelDO.getId());
                    gallery.setTitle(modelDO.getTitle());
                    gallery.setImageUrl(modelDO.getImageUrl());
                    gallery.setTargetType(modelDO.getTargetType());
                    gallery.setTargetUrl(modelDO.getTargetUrl());
                    gallery.setFlagHomeSlide(modelDO.getFlagHomeSlide());
                    gallery.setFlagPortfolioSlide(modelDO.getFlagPortfolioSlide());
                    gallery.setFlagMidBanner(modelDO.getFlagMidBanner());
                    gallery.setCreatedTime(modelDO.getCreatedTime());
                    gallery.setSorting(modelDO.getSorting());
                    gallery.setStatus(modelDO.getStatus());
                    return gallery;
                })
                .collect(Collectors.toList());

        PageResult<WebImageGallery> pageResult = new PageResult<>();
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

    public String updateImageGallery(String orgId, String itemId, String section, String value) {
        AppImageGalleryDO appImageGalleryDO = appImageGalleryRepository.findByIdAndOrgId(itemId, orgId);
        AssertUtil.notNull(appImageGalleryDO, EzErrorCode.DATA_NOT_FOUND);

        if (StringUtil.equals(section, "title")) {
            appImageGalleryDO.setTitle(value);
        }
        if (StringUtil.equals(section, "status")) {
            appImageGalleryDO.setStatus(Integer.parseInt(value));
        }
        if (StringUtil.equals(section, "home")) {
            appImageGalleryDO.setFlagHomeSlide(Integer.parseInt(value));
        }
        if (StringUtil.equals(section, "portfolio")) {
            appImageGalleryDO.setFlagPortfolioSlide(Integer.parseInt(value));
        }
        if (StringUtil.equals(section, "midbanner")) {
            appImageGalleryDO.setFlagMidBanner(Integer.parseInt(value));
        }
        if (StringUtil.equals(section, "sorting")) {
            appImageGalleryDO.setSorting(Integer.parseInt(value));
        }
        appImageGalleryRepository.saveAndFlush(appImageGalleryDO);
        return appImageGalleryDO.getId();
    }
}