/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process;

import id.ezclouds.common.facade.dal.area.AreaDistrictDAO;
import id.ezclouds.common.facade.dal.area.AreaVillageDAO;
import id.ezclouds.common.facade.process.MemberImportProcessor;
import id.ezclouds.common.model.area.District;
import id.ezclouds.common.model.area.Village;
import id.ezclouds.common.model.constant.OrgConstant;
import id.ezclouds.common.model.member.BizMemberImport;
import id.ezclouds.common.model.request.FileStreamImportRequest;
import id.ezclouds.common.model.result.BaseResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.process.biz.BizMemberImportProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import id.ezclouds.core.process.template.CoreProcessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberImportProcessor.java, v 0.1 2024‐08‐11 5:09 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberImportProcessor implements MemberImportProcessor {

    @Autowired
    private BizMemberImportProcessor bizMemberImportProcessor;

    @Autowired
    private AreaDistrictDAO areaDistrictDAO;

    @Autowired
    private AreaVillageDAO areaVillageDAO;

    private Map<String, String> districtIdMap = new HashMap<>();
    private Map<String, String> villageIdMap = new HashMap<>();

    @Override
    public BaseResult process(FileStreamImportRequest request) {
        final BaseResult baseResult = new BaseResult();
        final List<String> logData = new ArrayList<>();

        CoreProcessTemplate.execute(BizProcessEvent.MEMBER_IMPORT_CSV, new CoreProcessTemplate.Handler() {
            @Override
            public void doStart(BizProcessEvent processEvent) {

            }

            @Override
            public boolean doProcess(BizProcessEvent processEvent) {
                //TODO: add request validation later
                String orgId = request.getOrgId();
                String subOrgId = request.getSubOrgId();

                if (OrgConstant.ORG_ID_RJL.equals(orgId)) {
                    loadAndMapDistricts(Collections.singletonList("1802"));
                    loadAndMapVillages(new ArrayList<>(districtIdMap.values()));
                }

                bizMemberImportProcessor.deleteAllImport(orgId, subOrgId);
                bizMemberImportProcessor.deleteAllImportFailed(orgId, subOrgId);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
                bufferedReader
                        .lines()
                        .forEach(line -> {
                            if (StringUtil.isNotBlank(line)) {
                                processCsvDataLine(line, orgId, subOrgId);
                            }
                        });

                baseResult.setSuccess(true);
                baseResult.setObject("PROCESS SUCCESS");
                return true;
            }

            @Override
            public void doFinish(BizProcessEvent processEvent) {

            }

            @Override
            public List<String> getLogData() {
                return logData;
            }
        });

        return baseResult;
    }

    private void loadAndMapDistricts(List<String> regencyIds) {
        List<District> districts = areaDistrictDAO.getByRegencyIds(regencyIds);
        for (District district : districts) {
            districtIdMap.put(district.getName(), district.getId());
        }
    }

    private void loadAndMapVillages(List<String> districtIds) {
        List<Village> villages = areaVillageDAO.getByDistrictIds(districtIds);
        for (Village village : villages) {
            villageIdMap.put(village.getName(), village.getId());
        }
    }

    private void processCsvDataLine(String line, String orgId, String subOrgId) {
        String currentTime = DateUtil.getCurrentFormattedDate();
        BizMemberImport memberImport = composeMemberImport(line.split(","), orgId, subOrgId, currentTime);
        if (memberImport != null) {

            try {
                bizMemberImportProcessor.storeMember(memberImport);
            } catch (Exception e) {
                e.printStackTrace();

                try {
                    bizMemberImportProcessor.storeMemberFailed(memberImport);
                } catch (Exception ignored1) {}
            }
        }
    }

    private BizMemberImport composeMemberImport(String[] columnData, String orgId, String subOrgId, String currentTime) {
        String name = fetchSafeColumnData(columnData, 0);
        if ("NAMA".equals(name)) {
            return null;
        }
        String gender = fetchSafeColumnData(columnData, 1);
        String ageGroup = fetchSafeColumnData(columnData, 2);
        String phone = fetchSafeColumnData(columnData, 3);
        String idCardNumber = fetchSafeColumnData(columnData, 8);

        BizMemberImport memberImport = new BizMemberImport();
        memberImport.setBizMemberId(HashUtil.createHash(orgId, subOrgId, name, gender, ageGroup, phone, idCardNumber, currentTime));
        memberImport.setOrgId(orgId);
        memberImport.setSubOrgId(subOrgId);
        memberImport.setSourceId("ADMIN_UPLOAD_CSV");
        memberImport.setName(name);
        memberImport.setGender(parseGender(gender));
        memberImport.setAgeGroup(ageGroup);
        memberImport.setPhone(phone);
        memberImport.setEducation(fetchSafeColumnData(columnData, 4));
        memberImport.setOccupation(fetchSafeColumnData(columnData, 5));
        memberImport.setReligion(fetchSafeColumnData(columnData, 6));
        memberImport.setEthnic(fetchSafeColumnData(columnData, 7));
        memberImport.setIdCardNumber(idCardNumber);
        memberImport.setDistrictName(fetchSafeColumnData(columnData, 9));
        memberImport.setVillageName(fetchSafeColumnData(columnData, 10));
        memberImport.setTpsNumber(parseTpsNumber(fetchSafeColumnData(columnData, 11)));
        memberImport.setCreatedTime(currentTime);

        if (OrgConstant.ORG_ID_RJL.equals(orgId)) {
            memberImport.setProvinceId("18");
            memberImport.setProvinceName("LAMPUNG");
            memberImport.setRegencyId("1802");
            memberImport.setRegencyName("KABUPATEN TANGGAMUS");
            memberImport.setDistrictId(districtIdMap.get(memberImport.getDistrictName()));
            memberImport.setVillageId(villageIdMap.get(memberImport.getVillageName()));
        }

        return memberImport;
    }

    private String fetchSafeColumnData(String[] data, int index) {
        if (index >= data.length) {
            return null;
        }
        return data[index];
    }

    private String parseGender(String gender) {
        if ("L".equals(gender)) {
            return "MALE";
        }
        if ("P".equals(gender)) {
            return "FEMALE";
        }
        return null;
    }

    private String parseTpsNumber(String tpsNo) {
        if (StringUtil.isNotBlank(tpsNo)) {
            if (tpsNo.length() < 2) {
                return "0" + tpsNo;
            }
            if (tpsNo.length() > 3) {
                return null;
            }
            return tpsNo;
        }
        return null;
    }
}