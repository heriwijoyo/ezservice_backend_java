/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.election;

import id.ezclouds.common.model.biz.election.BizVoterInvalid;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterInvalidDAO.java, v 0.1 2024‐10‐06 10:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizVoterInvalidDAO {

    void store(BizVoterInvalid bizVoter);
}