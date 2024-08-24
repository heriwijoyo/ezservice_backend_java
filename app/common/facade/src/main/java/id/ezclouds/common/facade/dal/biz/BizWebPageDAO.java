/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.BizWebPage;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizWebPageDAO.java, v 0.1 2024‐08‐24 10:27 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizWebPageDAO {

    BizWebPage getWebPage(String pageId);
}