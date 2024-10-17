/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.core;

import id.ezclouds.common.model.core.CoreBizValidation;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizValidationDAO.java, v 0.1 2024‐10‐01 1:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreBizValidationDAO {

    CoreBizValidation getBizValidation(String orgId, String scene);
}