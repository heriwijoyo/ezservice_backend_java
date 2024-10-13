/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportPage.java, v 0.1 2024‐10‐13 6:10 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportPage {

    private String reportPageId;
    private String orgId;
    private BizReportSection section;
    private String code;
    private String title;
    private String authType;
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

    public BizReportSection getSection() {
        return section;
    }

    public void setSection(BizReportSection section) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}