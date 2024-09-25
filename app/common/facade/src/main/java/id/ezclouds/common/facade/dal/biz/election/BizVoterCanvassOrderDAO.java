/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.election;

import id.ezclouds.common.model.biz.election.BizCanvassOrder;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterCanvassOrderDAO.java, v 0.1 2024‐09‐26 12:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizVoterCanvassOrderDAO {

    void store(BizCanvassOrder canvassOrder);

    BizCanvassOrder getCanvassOrder(String canvassOrderId);
}