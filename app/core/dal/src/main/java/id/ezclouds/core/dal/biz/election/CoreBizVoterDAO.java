/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election;

import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.query.BizGroupQueryCount;
import id.ezclouds.core.dal.biz.election.converter.BizVoterConverter;
import id.ezclouds.core.dal.biz.election.repo.BizElectionVoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @EzDAOLogger
    public List<BizVoter> getVoterDataPollStation(String districtId, String villageId, String pollStation) {
        BizVoterConverter converter = new BizVoterConverter();
        return bizElectionVoterRepository
                .findByOrgIdAndDistrictIdAndVillageIdAndPollStationId("RJL0", districtId, villageId, pollStation)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());

    }

    @Override
    @EzDAOLogger
    public List<BizVoter> getVoterByReferrer(String orgId, String referrerId) {
        BizVoterConverter converter = new BizVoterConverter();
        return bizElectionVoterRepository
                .findByOrgIdAndReferrerId(orgId, referrerId)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }

    @Override
    @EzDAOLogger
    public List<BizGroupQueryCount> countGroupBySubOrgWithinDate(String orgId, String startDate, String endDate) {
        return bizElectionVoterRepository
                .countGroupBySubOrgWithinDate(orgId, startDate, endDate);
    }

    @Override
    @EzDAOLogger
    public List<BizGroupQueryCount> countGroupByCoreAreaWithinDate(String orgId, CoreAreaLevel coreAreaLevel, String startDate, String endDate) {
        switch (coreAreaLevel) {
            case REGENCY:
                return bizElectionVoterRepository
                        .countGroupByRegencyWithinDate(orgId, startDate, endDate);
            case DISTRICT:
                return bizElectionVoterRepository
                        .countGroupByDistrictWithinDate(orgId, startDate, endDate);
            case VILLAGE:
                return bizElectionVoterRepository
                        .countGroupByVillageWithinDate(orgId, startDate, endDate);
        }
        return new ArrayList<>();
    }

    @Override
    public int countWithinDate(String orgId, String startDate, String endDate) {
        return (int) bizElectionVoterRepository
                .countWithinDate(orgId, startDate, endDate);
    }
}