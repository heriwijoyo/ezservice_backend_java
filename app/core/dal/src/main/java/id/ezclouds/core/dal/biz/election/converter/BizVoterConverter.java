/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.converter;

import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.election.dataobject.BizElectionVoterDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterConverter.java, v 0.1 2024‐09‐23 11:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizVoterConverter extends CommonDOModelConverter<BizElectionVoterDO, BizVoter> {

    @Override
    protected BizVoter safeConvertQuery(BizElectionVoterDO dataObject) {
        BizVoter bizVoter = new BizVoter();
        bizVoter.setVoterId(dataObject.getVoterId());
        bizVoter.setOrgId(dataObject.getOrgId());
        bizVoter.setSubOrgId(dataObject.getSubOrgId());
        bizVoter.setShard(dataObject.getShard());
        bizVoter.setSourceId(dataObject.getSourceId());
        bizVoter.setReferrerId(dataObject.getReferrerId());
        bizVoter.setFamilySize(dataObject.getFamilySize());
        bizVoter.setFamilySizeMale(dataObject.getFamilySizeMale());
        bizVoter.setFamilySizeFemale(dataObject.getFamilySizeFemale());
        bizVoter.setName(dataObject.getName());
        bizVoter.setGender(dataObject.getGender());
        bizVoter.setDateOfBirth(dataObject.getDateOfBirth());
        bizVoter.setPhone(dataObject.getPhone());
        bizVoter.setEducation(dataObject.getEducation());
        bizVoter.setOccupation(dataObject.getOccupation());
        bizVoter.setReligion(dataObject.getReligion());
        bizVoter.setEthnic(dataObject.getEthnic());
        bizVoter.setEmail(dataObject.getEmail());
        bizVoter.setIdCardNumber(dataObject.getIdCardNumber());
        bizVoter.setFamilyCardNumber(dataObject.getFamilyCardNumber());
        bizVoter.setProvinceId(dataObject.getProvinceId());
        bizVoter.setProvinceName(dataObject.getProvinceName());
        bizVoter.setRegencyId(dataObject.getRegencyId());
        bizVoter.setRegencyName(dataObject.getRegencyName());
        bizVoter.setDistrictId(dataObject.getDistrictId());
        bizVoter.setDistrictName(dataObject.getDistrictName());
        bizVoter.setVillageId(dataObject.getVillageId());
        bizVoter.setVillageName(dataObject.getVillageName());
        bizVoter.setNeighbourhood(dataObject.getNeighbourhood());
        bizVoter.setSubNeighbourhood(dataObject.getSubNeighbourhood());
        bizVoter.setPollStationId(dataObject.getPollStationId());
        bizVoter.setCreatedTime(dataObject.getCreatedTime());
        bizVoter.setModifiedTime(dataObject.getModifiedTime());
        bizVoter.setStatus(dataObject.getStatus());
        return bizVoter;
    }

    @Override
    protected BizElectionVoterDO safeConvertStore(BizVoter model) {
        BizElectionVoterDO voterDO = new BizElectionVoterDO();
        voterDO.setVoterId(model.getVoterId());
        voterDO.setOrgId(model.getOrgId());
        voterDO.setSubOrgId(model.getSubOrgId());
        voterDO.setShard(model.getShard());
        voterDO.setSourceId(model.getSourceId());
        voterDO.setReferrerId(model.getReferrerId());
        voterDO.setFamilySize(model.getFamilySize());
        voterDO.setFamilySizeMale(model.getFamilySizeMale());
        voterDO.setFamilySizeFemale(model.getFamilySizeFemale());
        voterDO.setName(model.getName());
        voterDO.setGender(model.getGender());
        voterDO.setDateOfBirth(model.getDateOfBirth());
        voterDO.setPhone(model.getPhone());
        voterDO.setEducation(model.getEducation());
        voterDO.setOccupation(model.getOccupation());
        voterDO.setReligion(model.getReligion());
        voterDO.setEthnic(model.getEthnic());
        voterDO.setEmail(model.getEmail());
        voterDO.setIdCardNumber(model.getIdCardNumber());
        voterDO.setFamilyCardNumber(model.getFamilyCardNumber());
        voterDO.setProvinceId(model.getProvinceId());
        voterDO.setProvinceName(model.getProvinceName());
        voterDO.setRegencyId(model.getRegencyId());
        voterDO.setRegencyName(model.getRegencyName());
        voterDO.setDistrictId(model.getDistrictId());
        voterDO.setDistrictName(model.getDistrictName());
        voterDO.setVillageId(model.getVillageId());
        voterDO.setVillageName(model.getVillageName());
        voterDO.setNeighbourhood(model.getNeighbourhood());
        voterDO.setSubNeighbourhood(model.getSubNeighbourhood());
        voterDO.setPollStationId(model.getPollStationId());
        voterDO.setCreatedTime(model.getCreatedTime());
        voterDO.setModifiedTime(model.getModifiedTime());
        voterDO.setStatus(model.getStatus());
        return voterDO;
    }
}