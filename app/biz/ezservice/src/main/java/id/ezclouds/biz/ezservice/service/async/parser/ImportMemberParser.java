/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.parser;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;

import java.util.Date;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ImportMemberParser.java, v 0.1 2024‐07‐07 8:13 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ImportMemberParser implements CSVLineParser<BizMemberImportDO> {

    private final String orgId;
    private final String subOrgId;
    private final String fileId;
    private int count = 0;

    public ImportMemberParser(String orgId, String subOrgId, String fileId) {
        this.orgId = orgId;
        this.subOrgId = subOrgId;
        this.fileId = fileId;
    }

    @Override
    public BizMemberImportDO parseLine(String[] line) {
        String currentTime = DateUtil.getCurrentFormattedDate();
        BizMemberImportDO memberImportDO = new BizMemberImportDO();
        memberImportDO.setBizMemberId(HashUtil.createHash(orgId, fileId, currentTime, fetchCounter()));
        memberImportDO.setOrgId(orgId);
        memberImportDO.setSubOrgId(subOrgId);
        memberImportDO.setSourceId(fileId);
        memberImportDO.setCreatedTime(currentTime);

        memberImportDO.setName(getValue(line, 1));
        memberImportDO.setGender(getValue(line, 2));
        memberImportDO.setDateOfBirth(reformatDate(getValue(line, 3)));
        memberImportDO.setPhone(getValue(line, 4));
        memberImportDO.setOccupation(getValue(line, 6));
        memberImportDO.setReligion(getValue(line, 7));
        memberImportDO.setEthnic(getValue(line, 8));
        memberImportDO.setIdCardNumber(getValue(line, 9));
        memberImportDO.setAddress(getValue(line, 12));
        memberImportDO.setTpsNumber(getValue(line, 13));

        return memberImportDO;
    }

    private String fetchCounter() {
        count++;
        return String.valueOf(count);
    }

    private String getValue(String[] line, int index) {
        if (line.length > index) {
            return line[index];
        }
        return null;
    }

    private String reformatDate(String dateStr) {
        Date date = DateUtil.parseFormattedDate("dd-MM-yyyy");
        return DateUtil.getFormattedDate(date);
    }
}