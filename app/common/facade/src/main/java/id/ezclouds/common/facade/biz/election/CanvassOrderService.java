/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.election;

import id.ezclouds.common.model.biz.election.BizCanvassOrder;
import id.ezclouds.common.model.biz.election.BizVoter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CanvassOrderService.java, v 0.1 2024‐09‐24 12:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CanvassOrderService {

    BizCanvassOrder createCanvassOrder(BizVoter bizVoter);
}