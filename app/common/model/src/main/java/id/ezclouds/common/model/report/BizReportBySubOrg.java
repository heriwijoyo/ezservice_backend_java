/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportBySubOrg.java, v 0.1 2024‐08‐01 1:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportBySubOrg {

    private String orgId;
    private String source;
    private String subOrgName;
    private int voterTotal;
    private int voterStrong;
    private int voterLazy;
    private int genderMale;
    private int genderFemale;
    private int genderOther;

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

    public String getSubOrgName() {
        return subOrgName;
    }

    public void setSubOrgName(String subOrgName) {
        this.subOrgName = subOrgName;
    }

    public int getVoterTotal() {
        return voterTotal;
    }

    public void setVoterTotal(int voterTotal) {
        this.voterTotal = voterTotal;
    }

    public int getVoterStrong() {
        return voterStrong;
    }

    public void setVoterStrong(int voterStrong) {
        this.voterStrong = voterStrong;
    }

    public int getVoterLazy() {
        return voterLazy;
    }

    public void setVoterLazy(int voterLazy) {
        this.voterLazy = voterLazy;
    }

    public int getGenderMale() {
        return genderMale;
    }

    public void setGenderMale(int genderMale) {
        this.genderMale = genderMale;
    }

    public int getGenderFemale() {
        return genderFemale;
    }

    public void setGenderFemale(int genderFemale) {
        this.genderFemale = genderFemale;
    }

    public int getGenderOther() {
        return genderOther;
    }

    public void setGenderOther(int genderOther) {
        this.genderOther = genderOther;
    }
}