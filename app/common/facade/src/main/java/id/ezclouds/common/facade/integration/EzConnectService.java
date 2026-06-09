/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.integration;

import id.ezclouds.common.model.integration.EzConnectResult;
import id.ezclouds.common.model.integration.WhatsappLogRequest;
import id.ezclouds.common.model.integration.WhatsappResendRequest;
import id.ezclouds.common.model.integration.WhatsappSendRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzConnectService.java, v 0.1 2024‐08‐14 7:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface EzConnectService {
    EzConnectResult sendWhatsappMessage(WhatsappSendRequest request);
    EzConnectResult getWhatsappLog(WhatsappLogRequest request);
    EzConnectResult resendWhatsapp(WhatsappResendRequest request);
}