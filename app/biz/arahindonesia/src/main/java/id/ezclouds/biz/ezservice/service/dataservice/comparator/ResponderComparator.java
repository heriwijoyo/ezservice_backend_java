/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.comparator;

import id.ezclouds.biz.ezservice.service.dataservice.dataobject.BizSurveyResponderDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ResponderComparator.java, v 0.1 2024‐02‐17 8:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ResponderComparator implements java.util.Comparator<BizSurveyResponderDO> {
    @Override
    public int compare(BizSurveyResponderDO o1, BizSurveyResponderDO o2) {
        return o1.getSorting() - o2.getSorting();
    }
}