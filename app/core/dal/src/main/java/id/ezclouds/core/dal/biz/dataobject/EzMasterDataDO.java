/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzMasterDataDO.java, v 0.1 2024‐09‐04 8:53 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_master_data")
public class EzMasterDataDO {

    @Id
    @Column(name = "biz_master_id")
    private String bizMasterId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "scene")
    private String scene;

    @Column(name = "data_id")
    private String dataId;

    @Column(name = "data_name")
    private String dataName;

    @Column(name = "number_value1")
    private Integer numberValue1;

    @Column(name = "number_value2")
    private Integer numberValue2;

    @Column(name = "number_value3")
    private Integer numberValue3;

    @Column(name = "number_value4")
    private Integer numberValue4;

    @Column(name = "number_value5")
    private Integer numberValue5;

    @Column(name = "char_value1")
    private String charValue1;

    @Column(name = "char_value2")
    private String charValue2;

    @Column(name = "char_value3")
    private String charValue3;

    @Column(name = "char_value4")
    private String charValue4;

    @Column(name = "char_value5")
    private String charValue5;

    public String getBizMasterId() {
        return bizMasterId;
    }

    public void setBizMasterId(String bizMasterId) {
        this.bizMasterId = bizMasterId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Integer getNumberValue1() {
        return numberValue1;
    }

    public void setNumberValue1(Integer numberValue1) {
        this.numberValue1 = numberValue1;
    }

    public Integer getNumberValue2() {
        return numberValue2;
    }

    public void setNumberValue2(Integer numberValue2) {
        this.numberValue2 = numberValue2;
    }

    public Integer getNumberValue3() {
        return numberValue3;
    }

    public void setNumberValue3(Integer numberValue3) {
        this.numberValue3 = numberValue3;
    }

    public Integer getNumberValue4() {
        return numberValue4;
    }

    public void setNumberValue4(Integer numberValue4) {
        this.numberValue4 = numberValue4;
    }

    public Integer getNumberValue5() {
        return numberValue5;
    }

    public void setNumberValue5(Integer numberValue5) {
        this.numberValue5 = numberValue5;
    }

    public String getCharValue1() {
        return charValue1;
    }

    public void setCharValue1(String charValue1) {
        this.charValue1 = charValue1;
    }

    public String getCharValue2() {
        return charValue2;
    }

    public void setCharValue2(String charValue2) {
        this.charValue2 = charValue2;
    }

    public String getCharValue3() {
        return charValue3;
    }

    public void setCharValue3(String charValue3) {
        this.charValue3 = charValue3;
    }

    public String getCharValue4() {
        return charValue4;
    }

    public void setCharValue4(String charValue4) {
        this.charValue4 = charValue4;
    }

    public String getCharValue5() {
        return charValue5;
    }

    public void setCharValue5(String charValue5) {
        this.charValue5 = charValue5;
    }
}