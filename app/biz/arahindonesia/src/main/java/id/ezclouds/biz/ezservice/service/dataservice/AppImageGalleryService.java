/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.service.dataservice.dataobject.AppImageGalleryDO;
import id.ezclouds.biz.ezservice.service.dataservice.repo.AppImageGalleryRepository;
import id.ezclouds.biz.ezservice.service.dataservice.request.AppImageGalleryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        galleryDO.setOrgId(request.getOrgId());
        galleryDO.setTitle(request.getTitle());
        galleryDO.setImageUrl(request.getImageUrl());
        galleryDO.setTargetType(request.getTargetType());
        galleryDO.setTargetUrl(request.getTargetUrl());
        galleryDO.setFlagHomeSlide(request.getFlagHomeSlide());
        galleryDO.setFlagPortfolioSlide(request.getFlagPortfolioSlide());
        galleryDO.setSorting(request.getSorting());
        galleryDO.setStatus(request.getStatus());

        appImageGalleryRepository.saveAndFlush(galleryDO);
    }
}