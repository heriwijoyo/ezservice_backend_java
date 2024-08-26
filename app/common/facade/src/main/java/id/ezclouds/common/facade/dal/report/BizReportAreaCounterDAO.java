/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.report;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAreaCounterDAO.java, v 0.1 2024‐08‐26 11:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportAreaCounterDAO {

    long countTotalVoter(String orgId, List<String> sources, String districtId, String villageId);
}