/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.broker;

import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.event.OverallReportChangeEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreEventPublisherService.java, v 0.1 2024‐09‐18 1:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreEventPublisherService {

    void publish(OverallReportChangeEvent event);

    void publish(EzCommonEvent ezCommonEvent);
}