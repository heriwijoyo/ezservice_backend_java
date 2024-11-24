/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.report;

import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportSurveyService.java, v 0.1 2024‐11‐23 8:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportSurveyService {

    BizResult getSurveyRecap(String tokenId);
}