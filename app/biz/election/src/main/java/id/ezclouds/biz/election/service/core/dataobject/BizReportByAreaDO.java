/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.core.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportByAreaDO.java, v 0.1 2024‐07‐15 11:23 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_by_area")
public class BizReportByAreaDO {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "source")
    private String source;
    @Column(name = "district_name")
    private String districtName;
    @Column(name = "village_name")
    private String villageName;
    @Column(name = "voter_total")
    private long voterTotal;
    @Column(name = "voter_strong")
    private long voterStrong;
    @Column(name = "voter_lazy")
    private long voterLazy;
    @Column(name = "voter_other")
    private long voterOther;
    @Column(name = "gender_male")
    private long genderMale;
    @Column(name = "gender_female")
    private long genderFemale;
    @Column(name = "gender_other")
    private long genderOther;
    @Column(name = "tps_data")
    private String tpsData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public long getVoterTotal() {
        return voterTotal;
    }

    public void setVoterTotal(long voterTotal) {
        this.voterTotal = voterTotal;
    }

    public long getVoterStrong() {
        return voterStrong;
    }

    public void setVoterStrong(long voterStrong) {
        this.voterStrong = voterStrong;
    }

    public long getVoterLazy() {
        return voterLazy;
    }

    public void setVoterLazy(long voterLazy) {
        this.voterLazy = voterLazy;
    }

    public long getVoterOther() {
        return voterOther;
    }

    public void setVoterOther(long voterOther) {
        this.voterOther = voterOther;
    }

    public long getGenderMale() {
        return genderMale;
    }

    public void setGenderMale(long genderMale) {
        this.genderMale = genderMale;
    }

    public long getGenderFemale() {
        return genderFemale;
    }

    public void setGenderFemale(long genderFemale) {
        this.genderFemale = genderFemale;
    }

    public long getGenderOther() {
        return genderOther;
    }

    public void setGenderOther(long genderOther) {
        this.genderOther = genderOther;
    }

    public String getTpsData() {
        return tpsData;
    }

    public void setTpsData(String tpsData) {
        this.tpsData = tpsData;
    }
}