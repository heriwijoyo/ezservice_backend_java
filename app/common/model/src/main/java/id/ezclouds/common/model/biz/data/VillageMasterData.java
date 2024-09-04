/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VillageMasterData.java, v 0.1 2024‐09‐04 6:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class VillageMasterData {

    private String villageId;
    private String villageName;
    private Integer voterMale;
    private Integer voterFemale;
    private Integer voterTotal;
    private Integer pollStationTotal;

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public Integer getVoterMale() {
        return voterMale;
    }

    public void setVoterMale(Integer voterMale) {
        this.voterMale = voterMale;
    }

    public Integer getVoterFemale() {
        return voterFemale;
    }

    public void setVoterFemale(Integer voterFemale) {
        this.voterFemale = voterFemale;
    }

    public Integer getVoterTotal() {
        return voterTotal;
    }

    public void setVoterTotal(Integer voterTotal) {
        this.voterTotal = voterTotal;
    }

    public Integer getPollStationTotal() {
        return pollStationTotal;
    }

    public void setPollStationTotal(Integer pollStationTotal) {
        this.pollStationTotal = pollStationTotal;
    }
}