/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.model.event.BizEvent;
import id.ezclouds.biz.ezservice.service.app.dataobject.AppEventDO;
import id.ezclouds.biz.ezservice.service.app.repo.AppEventRepository;
import id.ezclouds.biz.ezservice.service.result.PageResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void updateEvent(BizEvent bizEvent) {
        AppEventDO eventDO = appEventRepository.findByIdAndOrgId(bizEvent.getId(), bizEvent.getOrgId());
        AssertUtil.notNull(eventDO, EzErrorCode.DATA_NOT_FOUND);

        eventDO.setTitle(bizEvent.getTitle());
        eventDO.setCategory(bizEvent.getCategory());
        eventDO.setDescription(bizEvent.getDescription());
        eventDO.setDateStart(bizEvent.getDateStart());
        eventDO.setDateEnd(bizEvent.getDateEnd());
        eventDO.setTimeStart(bizEvent.getTimeStart());
        eventDO.setTimeEnd(bizEvent.getTimeEnd());
        eventDO.setLocation(bizEvent.getLocation());
        eventDO.setModifiedTime(bizEvent.getModifiedTime());

        if (StringUtil.isNotBlank(bizEvent.getImageUrl())) {
            eventDO.setImageUrl(bizEvent.getImageUrl());
        }
        appEventRepository.saveAndFlush(eventDO);
    }

    @Transactional
    public void adminNewsFlagSwitch(String orgId, String eventId, String section, int value) {
        AppEventDO appEventDO = appEventRepository
                .findByIdAndOrgId(eventId, orgId);
        AssertUtil.notNull(appEventDO, EzErrorCode.DATA_NOT_FOUND);
        if (section.equals("status")) {
            appEventDO.setStatus(value);
        }
        if (section.equals("highlight")) {
            appEventDO.setHighlight(value);
        }
        appEventRepository.saveAndFlush(appEventDO);
    }

    public PageResult<BizEvent> getEvents(String orgId, PageRequest pageRequest) {
        Page<AppEventDO> findResult = appEventRepository
                .findByOrgId(orgId, pageRequest);

        List<BizEvent> resultData = findResult
                .getContent()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

        PageResult<BizEvent> pageResult = new PageResult<>();
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

    public BizEvent getEventDetail(String orgId, String eventId) throws EzErrorException {
        AppEventDO appEventDO = appEventRepository
                .findByIdAndOrgId(eventId, orgId);
        AssertUtil.notNull(appEventDO, EzErrorCode.DATA_NOT_FOUND);
        return convert(appEventDO);
    }

    private BizEvent convert(AppEventDO modelDO) {
        BizEvent bizEvent = new BizEvent();
        bizEvent.setId(modelDO.getId());
        bizEvent.setTitle(modelDO.getTitle());
        bizEvent.setDescription(modelDO.getDescription());
        bizEvent.setLocation(modelDO.getLocation());
        bizEvent.setImageUrl(modelDO.getImageUrl());
        bizEvent.setCategory(modelDO.getCategory());
        bizEvent.setDateStart(modelDO.getDateStart());
        bizEvent.setDateEnd(modelDO.getDateEnd());
        bizEvent.setTimeStart(modelDO.getTimeStart());
        bizEvent.setTimeEnd(modelDO.getTimeEnd());
        bizEvent.setHighlight(modelDO.getHighlight());
        bizEvent.setStatus(modelDO.getStatus());
        bizEvent.setCreatedTime(modelDO.getCreatedTime());
        return bizEvent;
    }
}