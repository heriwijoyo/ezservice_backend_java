/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.model.profile;

import id.ezclouds.biz.arahindonesia.model.ImageSlide;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateProfile.java, v 0.1 2023‐12‐10 3:00 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CandidateProfile {

    private String contactNumber;
    private String vision;
    private String mission;
    private List<ImageSlide> portfolios;
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

    public List<ImageSlide> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<ImageSlide> portfolios) {
        this.portfolios = portfolios;
    }

    public List<CandidateBio> getCandidateBios() {
        return candidateBios;
    }

    public void setCandidateBios(List<CandidateBio> candidateBios) {
        this.candidateBios = candidateBios;
    }
}