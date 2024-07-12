/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core.dataobject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportCustomDO.java, v 0.1 2024‐07‐12 8:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_custom_rjl")
public class BizReportCustomDO {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "group_type")
    private String groupType;
    @Column(name = "group_id")
    private String groupId;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "total")
    private long total;
    @Column(name = "with_phone")
    private long withPhone;
    @Column(name = "with_idcard")
    private long withIdCard;
    @Column(name = "male")
    private long male;
    @Column(name = "female")
    private long female;
    @Column(name = "genderless")
    private long genderless;
    @Column(name = "created_time")
    private String createdTime;

    public BizReportCustomDO() {
    }

    public BizReportCustomDO(String groupId, long total) {
        this.groupId = groupId;
        this.total = total;
    }

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

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getWithPhone() {
        return withPhone;
    }

    public void setWithPhone(long withPhone) {
        this.withPhone = withPhone;
    }

    public long getWithIdCard() {
        return withIdCard;
    }

    public void setWithIdCard(long withIdCard) {
        this.withIdCard = withIdCard;
    }

    public long getMale() {
        return male;
    }

    public void setMale(long male) {
        this.male = male;
    }

    public long getFemale() {
        return female;
    }

    public void setFemale(long female) {
        this.female = female;
    }

    public long getGenderless() {
        return genderless;
    }

    public void setGenderless(long genderless) {
        this.genderless = genderless;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}