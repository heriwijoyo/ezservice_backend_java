/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.converter;

import id.ezclouds.biz.ezservice.model.BizStatus;
import id.ezclouds.biz.ezservice.model.admin.BizAdminSession;
import id.ezclouds.core.auth.model.CoreAuthAdminSession;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminConverter.java, v 0.1 2024‐02‐10 6:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAdminConverter {

    public static BizAdminSession convert(CoreAuthAdminSession session) {
        if (session == null) { return null; }
        BizAdminSession bizSession = new BizAdminSession();
        bizSession.setSessionId(session.getSessionId());
        bizSession.setSessionCode(session.getSessionCode());
        bizSession.setExpiryTime(session.getExpiryTime());
        bizSession.setStatus(BizStatus.getByCode(session.getStatus()).getDescription());
        return bizSession;
    }
}