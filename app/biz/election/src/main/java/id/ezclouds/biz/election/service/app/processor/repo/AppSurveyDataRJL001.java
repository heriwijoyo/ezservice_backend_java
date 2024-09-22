/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.processor.repo;

import id.ezclouds.biz.election.model.annotation.InjectedValue;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyDataRJL001.java, v 0.1 2024‐05‐10 3:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "app_survey_data_rjl001")
public class AppSurveyDataRJL001 extends AppSurveyBaseData {

    @Column(name = "name")
    @InjectedValue
    private String name;

    @Column(name = "role")
    @InjectedValue
    private String role;

    @Column(name = "voter_size")
    @InjectedValue(type = Integer.class)
    private int voterSize;

    @Column(name = "occupation")
    @InjectedValue
    private String occupation;

    @Column(name = "education")
    @InjectedValue
    private String education;

    @Column(name = "gender")
    @InjectedValue
    private String gender;

    @Column(name = "religion")
    @InjectedValue
    private String religion;

    @Column(name = "ethnic")
    @InjectedValue
    private String ethnic;

    @Column(name = "phone")
    @InjectedValue
    private String phone;

    @Column(name = "age")
    @InjectedValue(type = Integer.class)
    private int age;

    @Column(name = "address")
    @InjectedValue
    private String address;

    @Column(name = "longitude")
    @InjectedValue
    private String longitude;

    @Column(name = "latitude")
    @InjectedValue
    private String latitude;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getVoterSize() {
        return voterSize;
    }

    public void setVoterSize(int voterSize) {
        this.voterSize = voterSize;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}