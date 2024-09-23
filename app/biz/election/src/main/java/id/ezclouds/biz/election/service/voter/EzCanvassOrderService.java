/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.CanvassOrderService;
import id.ezclouds.common.model.biz.election.BizCanvassOrder;
import id.ezclouds.common.model.biz.election.BizVoter;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCanvassOrderService.java, v 0.1 2024‐09‐24 12:30 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCanvassOrderService implements CanvassOrderService {

    @Override
    public BizCanvassOrder createCanvassOrder(BizVoter bizVoter) {
        return null;
    }
}