/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service;

import id.ezclouds.common.facade.broker.EzEventPublisherService;
import id.ezclouds.common.model.broker.event.EzCommonEventData;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreEventPublisherService.java, v 0.1 2024‐09‐18 1:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreEventPublisherService implements EzEventPublisherService {

    private final ApplicationEventPublisher eventPublisher;

    public CoreEventPublisherService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publish(EzCommonEventData eventData) {
        eventPublisher.publishEvent(eventData);
    }
}