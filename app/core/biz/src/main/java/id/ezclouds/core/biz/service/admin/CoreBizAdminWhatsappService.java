/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.admin;

import id.ezclouds.common.facade.biz.admin.BizAdminWhatsappService;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizAdminWhatsappService.java, v 0.1 2024‐08‐29 2:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBizAdminWhatsappService implements BizAdminWhatsappService {

    @Override
    public BizResult sendMessage(WebBizUpdateRequest<String> request) {
        return null;
    }
}