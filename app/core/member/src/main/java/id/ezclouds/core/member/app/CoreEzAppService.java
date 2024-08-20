/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.app;

import id.ezclouds.common.facade.app.EzAppService;
import id.ezclouds.common.model.app.EzApplication;
import id.ezclouds.common.model.constant.OrgConstant;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreEzAppService.java, v 0.1 2024‐08‐12 9:20 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreEzAppService implements EzAppService {
    @Override
    public EzApplication getActiveApp(String orgId) {
        if (OrgConstant.ORG_ID_RJL.equals(orgId)) {
            EzApplication app = new EzApplication();
            app.setId("TBA");
        }
        return null;
    }
}