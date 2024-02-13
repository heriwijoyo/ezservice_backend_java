/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.model.VideoCard;
import id.ezclouds.biz.ezservice.service.dataservice.request.VideoCardCreateRequest;
import id.ezclouds.common.dal.dataobject.VideoCardDO;
import id.ezclouds.common.dal.repo.VideoCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VideoCardService.java, v 0.1 2023‐12‐10 12:17 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class VideoCardService {

    @Autowired
    private VideoCardRepository videoCardRepository;

    @Cacheable("videoCard")
    public List<VideoCard> getAllVideoCards() {
        return videoCardRepository
                .findAllActive()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createVideoCard(VideoCardCreateRequest request) {
        VideoCardDO videoCardDO = new VideoCardDO();
        videoCardDO.setOrgId(request.getOrgId());
        videoCardDO.setSection(request.getSection());
        videoCardDO.setSectionName(request.getSectionName());
        videoCardDO.setTitle(request.getTitle());
        videoCardDO.setDescription(request.getDescription());
        videoCardDO.setThumbnail(request.getThumbnail());
        videoCardDO.setTargetType(request.getTargetType());
        videoCardDO.setTargetUrl(request.getTargetUrl());
        videoCardDO.setSorting(request.getSorting());
        videoCardDO.setStatus(request.getStatus());
        videoCardRepository.saveAndFlush(videoCardDO);
    }
}