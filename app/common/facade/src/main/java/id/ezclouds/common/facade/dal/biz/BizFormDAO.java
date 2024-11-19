/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.form.CoreBizForm;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizFormDAO.java, v 0.1 2024‐11‐18 7:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizFormDAO {

    CoreBizForm getById(String formId);
}