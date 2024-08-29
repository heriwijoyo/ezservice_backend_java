/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.admin;

import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminMemberUpdateService.java, v 0.1 2024‐08‐29 11:07 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAdminMemberUpdateService {

    BizResult updateMemberBackOffice(WebBizUpdateRequest<Map<String, String>> request);
}