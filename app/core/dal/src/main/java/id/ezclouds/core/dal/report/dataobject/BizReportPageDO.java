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
 * @version $Id: BizReportPageDO.java, v 0.1 2024‐10‐13 6:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_page")
public class BizReportPageDO {

    @Id
    @Column(name = "report_page_id")
    private String reportPageId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "section")
    private String section;
    @Column(name = "code")
    private String code;
    @Column(name = "title")
    private String title;
    @Column(name = "auth_type")
    private String authType;
    @Column(name = "status")
    private int status;
    @Column(name = "content")
    private String content;

    public String getReportPageId() {
        return reportPageId;
    }

    public void setReportPageId(String reportPageId) {
        this.reportPageId = reportPageId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}