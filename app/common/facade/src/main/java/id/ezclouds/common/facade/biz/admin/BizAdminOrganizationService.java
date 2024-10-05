/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.admin;

import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminOrganizationService.java, v 0.1 2024‐09‐23 2:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAdminOrganizationService {

    BizResult getOrganizations(String sessionId);

    BizResult initSystemSequence(String sessionId, String orgId);

    BizResult initMigrateMember(String sessionId, String orgId);
}