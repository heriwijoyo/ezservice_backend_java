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
 * @version $Id: EzCommonDataSurveyDO.java, v 0.1 2024‐08‐18 10:39 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "app_common_data_survey")
public class EzCommonDataSurveyDO {

    @Id
    @Column(name = "data_id")
    public String dataId;
    @Column(name = "org_id")
    public String orgId;
    @Column(name = "survey_id")
    public String surveyId;
    @Column(name = "response_id")
    public String responseId;
    @Column(name = "question_version")
    public String questionVersion;
    @Column(name = "submitter_id")
    public String submitterId;
    @Column(name = "submitter_name")
    public String submitterName;
    @Column(name = "created_time")
    public String createdTime;

    @Column(name = "r_001")
    public String r001;
    @Column(name = "r_002")
    public String r002;
    @Column(name = "r_003")
    public String r003;
    @Column(name = "r_004")
    public String r004;
    @Column(name = "r_005")
    public String r005;
    @Column(name = "r_006")
    public String r006;
    @Column(name = "r_007")
    public String r007;
    @Column(name = "r_008")
    public String r008;
    @Column(name = "r_009")
    public String r009;
    @Column(name = "r_010")
    public String r010;
    @Column(name = "r_011")
    public String r011;
    @Column(name = "r_012")
    public String r012;
    @Column(name = "r_013")
    public String r013;
    @Column(name = "r_014")
    public String r014;
    @Column(name = "r_015")
    public String r015;
    @Column(name = "r_016")
    public String r016;
    @Column(name = "r_017")
    public String r017;
    @Column(name = "r_018")
    public String r018;
    @Column(name = "r_019")
    public String r019;
    @Column(name = "r_020")
    public String r020;

    @Column(name = "q_001_a")
    public String q001a;
    @Column(name = "q_001_o")
    public String q001o;
    @Column(name = "q_002_a")
    public String q002a;
    @Column(name = "q_002_o")
    public String q002o;
    @Column(name = "q_003_a")
    public String q003a;
    @Column(name = "q_003_o")
    public String q003o;
    @Column(name = "q_004_a")
    public String q004a;
    @Column(name = "q_004_o")
    public String q004o;
    @Column(name = "q_005_a")
    public String q005a;
    @Column(name = "q_005_o")
    public String q005o;
    @Column(name = "q_006_a")
    public String q006a;
    @Column(name = "q_006_o")
    public String q006o;
    @Column(name = "q_007_a")
    public String q007a;
    @Column(name = "q_007_o")
    public String q007o;
    @Column(name = "q_008_a")
    public String q008a;
    @Column(name = "q_008_o")
    public String q008o;
    @Column(name = "q_009_a")
    public String q009a;
    @Column(name = "q_009_o")
    public String q009o;
    @Column(name = "q_010_a")
    public String q010a;
    @Column(name = "q_010_o")
    public String q010o;
    @Column(name = "q_011_a")
    public String q011a;
    @Column(name = "q_011_o")
    public String q011o;
    @Column(name = "q_012_a")
    public String q012a;
    @Column(name = "q_012_o")
    public String q012o;
    @Column(name = "q_013_a")
    public String q013a;
    @Column(name = "q_013_o")
    public String q013o;
    @Column(name = "q_014_a")
    public String q014a;
    @Column(name = "q_014_o")
    public String q014o;
    @Column(name = "q_015_a")
    public String q015a;
    @Column(name = "q_015_o")
    public String q015o;
    @Column(name = "q_016_a")
    public String q016a;
    @Column(name = "q_016_o")
    public String q016o;
    @Column(name = "q_017_a")
    public String q017a;
    @Column(name = "q_017_o")
    public String q017o;
    @Column(name = "q_018_a")
    public String q018a;
    @Column(name = "q_018_o")
    public String q018o;
    @Column(name = "q_019_a")
    public String q019a;
    @Column(name = "q_019_o")
    public String q019o;
    @Column(name = "q_020_a")
    public String q020a;
    @Column(name = "q_020_o")
    public String q020o;
}