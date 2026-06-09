/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.election;

import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterRegistrationService.java, v 0.1 2024‐09‐28 11:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizVoterRegistrationService {

    BizResult registerVoter(BizVoter bizVoter);
}