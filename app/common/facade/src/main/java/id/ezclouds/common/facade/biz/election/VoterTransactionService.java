/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.election;

import id.ezclouds.common.model.biz.election.BizVoter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VoterTransactionService.java, v 0.1 2024‐09‐23 10:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface VoterTransactionService {

    void canvassOrder(BizVoter voter);
}