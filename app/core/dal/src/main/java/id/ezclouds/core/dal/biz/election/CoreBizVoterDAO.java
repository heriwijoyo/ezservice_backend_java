/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election;

import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.core.dal.biz.election.converter.BizVoterConverter;
import id.ezclouds.core.dal.biz.election.repo.BizElectionVoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizVoterDAO.java, v 0.1 2024‐09‐23 11:04 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizVoterDAO implements BizVoterDAO {

    @Autowired
    private BizElectionVoterRepository bizElectionVoterRepository;

    @Override
    @EzDAOLogger
    public BizVoter getByIdCardNumber(String orgId, String idCardNumber) {
        return new BizVoterConverter().convertQuery(
                bizElectionVoterRepository
                        .findByOrgIdAndIdCardNumber(orgId, idCardNumber)
        );
    }

    @Override
    @EzDAOLogger
    public BizVoter getVoterById(String voterId) {
        return new BizVoterConverter().convertQuery(
                bizElectionVoterRepository
                        .findById(voterId)
                        .orElse(null)
        );
    }

    @Override
    @EzDAOLogger
    public void store(BizVoter voter) {
        bizElectionVoterRepository
                .saveAndFlush(new BizVoterConverter().convertStore(voter));
    }
}