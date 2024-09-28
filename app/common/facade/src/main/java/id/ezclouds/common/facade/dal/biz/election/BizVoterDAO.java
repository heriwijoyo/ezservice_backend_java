/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.election;

import id.ezclouds.common.model.biz.election.BizVoter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterDAO.java, v 0.1 2024‐09‐23 11:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizVoterDAO {

    BizVoter getByIdCardNumber(String orgId, String idCardNumber);

    BizVoter getVoterById(String voterId);

    void store(BizVoter voter);
}