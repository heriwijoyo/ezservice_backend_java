/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app;

import id.ezclouds.biz.election.model.VideoCard;
import id.ezclouds.biz.election.service.app.dataobject.VideoCardDO;
import id.ezclouds.biz.election.service.app.repo.VideoCardRepository;
import id.ezclouds.biz.election.converter.BizModelConverter;
import id.ezclouds.biz.election.service.app.request.VideoCardCreateRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.shared.model.CommonModelSwitch;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<VideoCard> getAllVideoCards() {
        return videoCardRepository
                .findAllActive()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createVideoCard(VideoCardCreateRequest request) {
        String currentTime = DateUtil.getCurrentFormattedDate();
        VideoCardDO videoCardDO = new VideoCardDO();
        videoCardDO.setId(HashUtil.createHash(request.getOrgId(), currentTime));
        videoCardDO.setOrgId(request.getOrgId());
        videoCardDO.setSection(request.getSection());
        videoCardDO.setSectionName(request.getSectionName());
        videoCardDO.setTitle(request.getTitle());
        videoCardDO.setDescription(request.getDescription());
        videoCardDO.setThumbnail(request.getThumbnail());
        videoCardDO.setTargetType(request.getTargetType());
        videoCardDO.setTargetUrl(request.getTargetUrl());
        videoCardDO.setCreatedTime(currentTime);
        videoCardDO.setModifiedTime(currentTime);
        videoCardDO.setSorting(request.getSorting());
        videoCardDO.setStatus(1);
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

    @Transactional
    public void updateVideoCard(VideoCard videoCard) {
        VideoCardDO videoCardDO = videoCardRepository
                .findByIdAndOrgId(videoCard.getId(), videoCard.getOrgId());
        AssertUtil.notNull(videoCardDO, EzErrorCode.DATA_NOT_FOUND);

        videoCardDO.setSection(videoCard.getSection());
        videoCardDO.setSectionName(videoCard.getSectionName());
        videoCardDO.setTargetUrl(videoCard.getTargetUrl());
        videoCardRepository.saveAndFlush(videoCardDO);
    }

    @Transactional
    public void switchFlag(CommonModelSwitch modelSwitch) {
        VideoCardDO videoCardDO = videoCardRepository
                .findByIdAndOrgId(modelSwitch.getItemId(), modelSwitch.getOrgId());
        AssertUtil.notNull(videoCardDO, EzErrorCode.DATA_NOT_FOUND);

        if ("status".equals(modelSwitch.getSection())) {
            videoCardDO.setStatus(Integer.parseInt(modelSwitch.getValue()));
        }
        videoCardRepository.saveAndFlush(videoCardDO);
    }

    private VideoCard convert(VideoCardDO videoCardDO) {
        if (videoCardDO == null) { return null; }
        VideoCard videoCard = new VideoCard();
        videoCard.setId(videoCardDO.getId());
        videoCard.setOrgId(videoCardDO.getOrgId());
        videoCard.setSection(videoCardDO.getSection());
        videoCard.setSectionName(videoCardDO.getSectionName());
        videoCard.setThumbnail(videoCardDO.getThumbnail());
        videoCard.setTargetType(videoCardDO.getTargetType());
        videoCard.setTargetUrl(videoCardDO.getTargetUrl());
        videoCard.setStatus(videoCardDO.getStatus());
        return videoCard;
    }
}