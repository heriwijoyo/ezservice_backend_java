/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.BizUnique;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizUniqueDAO.java, v 0.1 2024‐08‐31 3:19 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizUniqueDAO {

    BizUnique getUnique(String uniqueId);

    void storeUnique(BizUnique bizUnique);
}