/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election;

import id.ezclouds.common.facade.dal.biz.election.BizVoterInvalidDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.election.BizVoterInvalid;
import id.ezclouds.core.dal.biz.election.converter.BizVoterInvalidConverter;
import id.ezclouds.core.dal.biz.election.repo.BizVoterInvalidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizVoterInvalidDAO.java, v 0.1 2024‐10‐06 10:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizVoterInvalidDAO implements BizVoterInvalidDAO {

    @Autowired
    private BizVoterInvalidRepository bizVoterInvalidRepository;

    @Override
    @EzDAOLogger
    public void store(BizVoterInvalid bizVoter) {
        bizVoterInvalidRepository
                .saveAndFlush(
                        new BizVoterInvalidConverter()
                                .convertStore(bizVoter)
                );
    }

    @Override
    @EzDAOLogger
    public List<BizVoterInvalid> getByReferrerId(String orgId, String referrerId) {
        BizVoterInvalidConverter converter = new BizVoterInvalidConverter();
        return bizVoterInvalidRepository
                .findByOrgIdAndReferrerId(orgId, referrerId)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }
}