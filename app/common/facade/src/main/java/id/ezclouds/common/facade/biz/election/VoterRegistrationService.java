/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.election;

import id.ezclouds.common.model.biz.election.BizVoter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VoterRegistrationService.java, v 0.1 2024‐09‐23 9:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface VoterRegistrationService {

    String registerVoter(BizVoter bizVoter);
}