/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.model.event.AppEvent;
import id.ezclouds.biz.ezservice.model.event.AppEventHome;
import id.ezclouds.biz.ezservice.service.app.dataobject.AppEventDO;
import id.ezclouds.biz.ezservice.service.app.repo.AppEventRepository;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.biz.ezservice.util.PageRequestUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppEventService.java, v 0.1 2024‐04‐10 2:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppEventService {

    @Autowired
    private AppEventRepository appEventRepository;

    @Transactional
    public void createEvent(AppEvent appEvent) {
        AppEventDO eventDO = new AppEventDO();
        eventDO.setId(appEvent.getId());
        eventDO.setOrgId(appEvent.getOrgId());
        eventDO.setTitle(appEvent.getTitle());
        eventDO.setCategory(appEvent.getCategory());
        eventDO.setDescription(appEvent.getDescription());
        eventDO.setDateStart(appEvent.getDateStart());
        eventDO.setDateEnd(appEvent.getDateEnd());
        eventDO.setTimeStart(appEvent.getTimeStart());
        eventDO.setTimeEnd(appEvent.getTimeEnd());
        eventDO.setLocation(appEvent.getLocation());
        eventDO.setImageUrl(appEvent.getImageUrl());
        eventDO.setCreatedTime(appEvent.getCreatedTime());
        eventDO.setModifiedTime(appEvent.getModifiedTime());
        eventDO.setHighlight(appEvent.getHighlight());
        eventDO.setStatus(appEvent.getStatus());
        appEventRepository.saveAndFlush(eventDO);
    }

    @Transactional
    public void updateEvent(AppEvent appEvent) {
        AppEventDO eventDO = appEventRepository.findByIdAndOrgId(appEvent.getId(), appEvent.getOrgId());
        AssertUtil.notNull(eventDO, EzErrorCode.DATA_NOT_FOUND);

        eventDO.setTitle(appEvent.getTitle());
        eventDO.setCategory(appEvent.getCategory());
        eventDO.setDescription(appEvent.getDescription());
        eventDO.setDateStart(appEvent.getDateStart());
        eventDO.setDateEnd(appEvent.getDateEnd());
        eventDO.setTimeStart(appEvent.getTimeStart());
        eventDO.setTimeEnd(appEvent.getTimeEnd());
        eventDO.setLocation(appEvent.getLocation());
        eventDO.setModifiedTime(appEvent.getModifiedTime());

        if (StringUtil.isNotBlank(appEvent.getImageUrl())) {
            eventDO.setImageUrl(appEvent.getImageUrl());
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

    public PageResult<AppEvent> getEvents(String orgId, PageRequest pageRequest) {
        Page<AppEventDO> findResult = appEventRepository
                .findByOrgId(orgId, pageRequest);

        List<AppEvent> resultData = findResult
                .getContent()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

        PageResult<AppEvent> pageResult = new PageResult<>();
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

    public AppEvent getEventDetail(String orgId, String eventId) throws EzErrorException {
        AppEventDO appEventDO = appEventRepository
                .findByIdAndOrgId(eventId, orgId);
        AssertUtil.notNull(appEventDO, EzErrorCode.DATA_NOT_FOUND);
        return convert(appEventDO);
    }

    public AppEventHome getAppEventHome(String orgId) {
        BizPageRequest bizPageRequest = new BizPageRequest();
        bizPageRequest.setPageNumber(1);
        bizPageRequest.setPageSize(30);
        bizPageRequest.setSortBy("dateStart");
        bizPageRequest.setSort("ASC");
        PageRequest pageRequest = PageRequestUtil.composePageRequest(bizPageRequest);
        Page<AppEventDO> findResult = appEventRepository
                .findByOrgIdAndStatus(orgId, 1, pageRequest);

        AppEventDO highlightedEvent = null;
        List<AppEvent> commonEvents = new ArrayList<>();
        for (AppEventDO eventDO : findResult.getContent()) {
            if (eventDO.getHighlight() == 1 && highlightedEvent == null) {
                highlightedEvent = eventDO;
            } else {
                commonEvents.add(convert(eventDO));
            }
        }

        AppEventHome appEventHome = new AppEventHome();
        appEventHome.setHighlights(convert(highlightedEvent));
        appEventHome.setEvents(commonEvents);
        return appEventHome;
    }

    private AppEvent convert(AppEventDO modelDO) {
        if (modelDO == null) { return null; }
        AppEvent appEvent = new AppEvent();
        appEvent.setId(modelDO.getId());
        appEvent.setTitle(modelDO.getTitle());
        appEvent.setDescription(modelDO.getDescription());
        appEvent.setLocation(modelDO.getLocation());
        appEvent.setImageUrl(modelDO.getImageUrl());
        appEvent.setCategory(modelDO.getCategory());
        appEvent.setDateStart(modelDO.getDateStart());
        appEvent.setDateEnd(modelDO.getDateEnd());
        appEvent.setTimeStart(modelDO.getTimeStart());
        appEvent.setTimeEnd(modelDO.getTimeEnd());
        appEvent.setHighlight(modelDO.getHighlight());
        appEvent.setStatus(modelDO.getStatus());
        appEvent.setCreatedTime(modelDO.getCreatedTime());
        return appEvent;
    }
}