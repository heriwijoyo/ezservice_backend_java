/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.election;

import id.ezclouds.common.model.biz.election.BizVoter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VoterInvalidRegistrationService.java, v 0.1 2024‐10‐06 10:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface VoterInvalidRegistrationService {

    String registerVoterInvalid(BizVoter bizVoter, String invalidCode, String invalidMessage);
}