/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.profile;

import id.ezclouds.biz.ezservice.service.dataservice.model.AppImageGallery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCandidateProfile.java, v 0.1 2023‐12‐10 3:00 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizCandidateProfile {

    private String contactNumber;
    private String vision;
    private String mission;
    private List<AppImageGallery> portfolios;
    private List<CandidateBio> candidateBios;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public List<AppImageGallery> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<AppImageGallery> portfolios) {
        this.portfolios = portfolios;
    }

    public List<CandidateBio> getCandidateBios() {
        return candidateBios;
    }

    public void setCandidateBios(List<CandidateBio> candidateBios) {
        this.candidateBios = candidateBios;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}