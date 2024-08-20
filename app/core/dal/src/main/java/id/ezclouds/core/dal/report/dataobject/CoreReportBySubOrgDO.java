/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportByAreaDO.java, v 0.1 2024‐07‐31 4:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_by_suborg")
public class CoreReportBySubOrgDO {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "source")
    private String source;
    @Column(name = "suborg_name")
    private String subOrgName;
    @Column(name = "voter_total")
    private int voterTotal;
    @Column(name = "voter_strong")
    private int voterStrong;
    @Column(name = "voter_lazy")
    private int voterLazy;
    @Column(name = "gender_male")
    private int genderMale;
    @Column(name = "gender_female")
    private int genderFemale;
    @Column(name = "gender_other")
    private int genderOther;

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