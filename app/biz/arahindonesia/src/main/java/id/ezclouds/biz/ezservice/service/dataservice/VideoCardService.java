/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.model.VideoCard;
import id.ezclouds.biz.ezservice.model.event.BizEvent;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.AppEventDO;
import id.ezclouds.biz.ezservice.service.dataservice.request.VideoCardCreateRequest;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.VideoCardDO;
import id.ezclouds.biz.ezservice.service.dataservice.repo.VideoCardRepository;
import id.ezclouds.biz.ezservice.service.result.PageResult;
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

    public PageResult<VideoCard> getVideoCards(String orgId, PageRequest pageRequest) {
        Page<VideoCardDO> findResult = videoCardRepository
                .findByOrgId(orgId, pageRequest);

        List<VideoCard> resultData = findResult
                .getContent()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

        PageResult<VideoCard> pageResult = new PageResult<>();
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

    private VideoCard convert(VideoCardDO videoCardDO) {
        if (videoCardDO == null) { return null; }
        VideoCard videoCard = new VideoCard();
        videoCard.setSection(videoCardDO.getSection());
        videoCard.setSectionName(videoCardDO.getSectionName());
        videoCard.setThumbnail(videoCardDO.getThumbnail());
        videoCard.setTargetType(videoCardDO.getTargetType());
        videoCard.setTargetUrl(videoCardDO.getTargetUrl());
        videoCard.setStatus(videoCardDO.getStatus());
        return videoCard;
    }
}