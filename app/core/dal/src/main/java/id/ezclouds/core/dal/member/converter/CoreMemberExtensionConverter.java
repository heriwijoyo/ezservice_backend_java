/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.member.CoreMemberExtension;
import id.ezclouds.core.dal.member.dataobject.CoreMemberExtensionDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtensionConverter.java, v 0.1 2024‐10‐05 3:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberExtensionConverter extends CommonDOModelConverter<CoreMemberExtensionDO, CoreMemberExtension> {

    @Override
    protected CoreMemberExtension safeConvertQuery(CoreMemberExtensionDO dataObject) {
        CoreMemberExtension memberExtension = new CoreMemberExtension();
        memberExtension.setMemberExtensionId(dataObject.getMemberExtensionId());
        memberExtension.setMemberId(dataObject.getMemberId());
        memberExtension.setOrgId(dataObject.getOrgId());
        memberExtension.setShard(dataObject.getShard());
        memberExtension.setIdCardNumber(dataObject.getIdCardNumber());
        memberExtension.setIdCardDocUrl(dataObject.getIdCardDocUrl());
        memberExtension.setProvinceId(dataObject.getProvinceId());
        memberExtension.setProvinceName(dataObject.getProvinceName());
        memberExtension.setRegencyId(dataObject.getRegencyId());
        memberExtension.setRegencyName(dataObject.getRegencyName());
        memberExtension.setDistrictId(dataObject.getDistrictId());
        memberExtension.setDistrictName(dataObject.getDistrictName());
        memberExtension.setVillageId(dataObject.getVillageId());
        memberExtension.setVillageName(dataObject.getVillageName());
        memberExtension.setNeighbourhood(dataObject.getRukunWarga());
        memberExtension.setSubNeighbourhood(dataObject.getRukunTetangga());
        memberExtension.setPollStationId(dataObject.getTpsNumber());
        return memberExtension;
    }

    @Override
    protected CoreMemberExtensionDO safeConvertStore(CoreMemberExtension model) {
        CoreMemberExtensionDO memberExtensionDO = new CoreMemberExtensionDO();
        memberExtensionDO.setMemberExtensionId(model.getMemberExtensionId());
        memberExtensionDO.setMemberId(model.getMemberId());
        memberExtensionDO.setOrgId(model.getOrgId());
        memberExtensionDO.setShard(model.getShard());
        memberExtensionDO.setIdCardNumber(model.getIdCardNumber());
        memberExtensionDO.setIdCardDocUrl(model.getIdCardDocUrl());
        memberExtensionDO.setProvinceId(model.getProvinceId());
        memberExtensionDO.setProvinceName(model.getProvinceName());
        memberExtensionDO.setRegencyId(model.getRegencyId());
        memberExtensionDO.setRegencyName(model.getRegencyName());
        memberExtensionDO.setDistrictId(model.getDistrictId());
        memberExtensionDO.setDistrictName(model.getDistrictName());
        memberExtensionDO.setVillageId(model.getVillageId());
        memberExtensionDO.setVillageName(model.getVillageName());
        memberExtensionDO.setRukunWarga(model.getNeighbourhood());
        memberExtensionDO.setRukunTetangga(model.getSubNeighbourhood());
        memberExtensionDO.setTpsNumber(model.getPollStationId());
        return memberExtensionDO;
    }
}