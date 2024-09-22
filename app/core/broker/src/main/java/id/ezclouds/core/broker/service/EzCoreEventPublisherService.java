/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service;

import id.ezclouds.common.facade.broker.CoreEventPublisherService;
import id.ezclouds.common.model.broker.event.OverallReportChangeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreEventPublisherService.java, v 0.1 2024‐09‐18 1:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreEventPublisherService implements CoreEventPublisherService {

    private final ApplicationEventPublisher eventPublisher;

    public EzCoreEventPublisherService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publish(OverallReportChangeEvent event) {
        eventPublisher.publishEvent(event);
    }
}