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
 * @version $Id: CoreReportRealCountDO.java, v 0.1 2024‐09‐15 11:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_real_count")
public class CoreReportRealCountDO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "scene")
    private String scene;

    @Column(name = "scene_id")
    private String sceneId;

    @Column(name = "scene_parent")
    private String sceneParent;

    @Column(name = "updated_time")
    private String updatedTime;

    @Column(name = "count_a")
    private int countA;

    @Column(name = "count_b")
    private int countB;

    @Column(name = "count_c")
    private int countC;

    @Column(name = "count_d")
    private int countD;

    @Column(name = "count_e")
    private int countE;

    @Column(name = "count_f")
    private int countF;

    @Column(name = "count_g")
    private int countG;

    @Column(name = "count_h")
    private int countH;

    @Column(name = "count_i")
    private int countI;

    @Column(name = "count_j")
    private int countJ;

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

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneParent() {
        return sceneParent;
    }

    public void setSceneParent(String sceneParent) {
        this.sceneParent = sceneParent;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getCountA() {
        return countA;
    }

    public void setCountA(int countA) {
        this.countA = countA;
    }

    public int getCountB() {
        return countB;
    }

    public void setCountB(int countB) {
        this.countB = countB;
    }

    public int getCountC() {
        return countC;
    }

    public void setCountC(int countC) {
        this.countC = countC;
    }

    public int getCountD() {
        return countD;
    }

    public void setCountD(int countD) {
        this.countD = countD;
    }

    public int getCountE() {
        return countE;
    }

    public void setCountE(int countE) {
        this.countE = countE;
    }

    public int getCountF() {
        return countF;
    }

    public void setCountF(int countF) {
        this.countF = countF;
    }

    public int getCountG() {
        return countG;
    }

    public void setCountG(int countG) {
        this.countG = countG;
    }

    public int getCountH() {
        return countH;
    }

    public void setCountH(int countH) {
        this.countH = countH;
    }

    public int getCountI() {
        return countI;
    }

    public void setCountI(int countI) {
        this.countI = countI;
    }

    public int getCountJ() {
        return countJ;
    }

    public void setCountJ(int countJ) {
        this.countJ = countJ;
    }
}