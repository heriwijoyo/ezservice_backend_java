/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.model.event.BizEvent;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.AppEventDO;
import id.ezclouds.biz.ezservice.service.dataservice.repo.AppEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizEventInnerService.java, v 0.1 2024‐04‐10 2:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizEventInnerService {

    @Autowired
    private AppEventRepository appEventRepository;

    @Transactional
    public void createEvent(BizEvent bizEvent) {
        AppEventDO eventDO = new AppEventDO();
        eventDO.setId(bizEvent.getId());
        eventDO.setOrgId(bizEvent.getOrgId());
        eventDO.setTitle(bizEvent.getTitle());
        eventDO.setCategory(bizEvent.getCategory());
        eventDO.setDescription(bizEvent.getDescription());
        eventDO.setDateStart(bizEvent.getDateStart());
        eventDO.setDateEnd(bizEvent.getDateEnd());
        eventDO.setTimeStart(bizEvent.getTimeStart());
        eventDO.setTimeEnd(bizEvent.getTimeEnd());
        eventDO.setLocation(bizEvent.getLocation());
        eventDO.setImageUrl(bizEvent.getImageUrl());
        eventDO.setCreatedTime(bizEvent.getCreatedTime());
        eventDO.setModifiedTime(bizEvent.getModifiedTime());
        eventDO.setHighlight(bizEvent.getHighlight());
        eventDO.setStatus(bizEvent.getStatus());
        appEventRepository.saveAndFlush(eventDO);
    }
}