/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service;

import id.ezclouds.biz.arahindonesia.converter.ModelConverter;
import id.ezclouds.biz.arahindonesia.model.VideoCard;
import id.ezclouds.common.dal.VideoCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Cacheable("video_cards")
    public List<VideoCard> getAllVideoCards() {
        return videoCardRepository
                .findAllActive()
                .stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }
}