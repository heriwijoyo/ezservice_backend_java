/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.admin;

import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminWhatsappService.java, v 0.1 2024‐08‐29 2:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAdminWhatsappService {

    BizResult sendMessage(WebBizUpdateRequest<String> request);
}