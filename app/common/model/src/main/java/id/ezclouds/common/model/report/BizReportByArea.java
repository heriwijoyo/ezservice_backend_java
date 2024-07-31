/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportByArea.java, v 0.1 2024‐07‐31 4:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportByArea {

    private String orgId;
    private String source;
    private String districtName;
    private String villageName;
    private int voterTotal;
    private int voterStrong;
    private int voterLazy;
    private int genderMale;
    private int genderFemale;
    private int genderOther;
    private String tpsData;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public int getVoterTotal() {
        return voterTotal;
    }

    public void setVoterTotal(int voterTotal) {
        this.voterTotal = voterTotal;
    }

    public void addVoterTotal(int addition) {
        this.voterTotal += addition;
    }

    public int getVoterStrong() {
        return voterStrong;
    }

    public void setVoterStrong(int voterStrong) {
        this.voterStrong = voterStrong;
    }

    public void addVoterStrong(int addition) {
        this.voterStrong += addition;
    }

    public int getVoterLazy() {
        return voterLazy;
    }

    public void setVoterLazy(int voterLazy) {
        this.voterLazy = voterLazy;
    }

    public void addVoterLazy(int addition) {
        this.voterLazy += addition;
    }

    public int getGenderMale() {
        return genderMale;
    }

    public void setGenderMale(int genderMale) {
        this.genderMale = genderMale;
    }

    public void addGenderMale(int addition) {
        this.genderMale += addition;
    }

    public int getGenderFemale() {
        return genderFemale;
    }

    public void setGenderFemale(int genderFemale) {
        this.genderFemale = genderFemale;
    }

    public void addGenderFemale(int addition) {
        this.genderFemale += addition;
    }

    public int getGenderOther() {
        return genderOther;
    }

    public void setGenderOther(int genderOther) {
        this.genderOther = genderOther;
    }

    public void addGenderOther(int addition) {
        this.genderOther += addition;
    }

    public String getTpsData() {
        return tpsData;
    }

    public void setTpsData(String tpsData) {
        this.tpsData = tpsData;
    }
}