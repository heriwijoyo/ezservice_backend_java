/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.dataservice;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.converter.BizModelConverter;
import id.ezclouds.biz.arahindonesia.model.ImageSlide;
import id.ezclouds.common.dal.repo.ImageSlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ImageSlideService.java, v 0.1 2023‐12‐10 9:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class ImageSlideService {

    @Autowired
    private ImageSlideRepository imageSlideRepository;

    @Cacheable("imageSlide_home")
    public List<ImageSlide> getImageSlideHome() {
        return imageSlideRepository
                .findActiveSectionImageSlide(AppConstant.IMAGE_SLIDE_SECTION_HOME)
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Cacheable("home_poster")
    public List<ImageSlide> getHomePosterImage() {
        return imageSlideRepository
                .findActiveSectionImageSlide(AppConstant.IMAGE_SLIDE_HOME_POSTER)
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Cacheable("porfolio_images")
    public List<ImageSlide> getPortfolioImage() {
        return imageSlideRepository
                .findActiveSectionImageSlide(AppConstant.IMAGE_SLIDE_PORTFOLIO)
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }
}