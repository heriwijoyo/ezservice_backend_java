/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.dataservice.dataobject;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ConnectBaseLogDO.java, v 0.1 2024‐05‐16 3:43 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@MappedSuperclass
public class ConnectBaseLogDO {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "target")
    private String target;
    @Column(name = "message")
    private String message;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "status")
    private String status;
    @Column(name = "response_time")
    private String responseTime;
    @Column(name = "response")
    private String response;
    @Column(name = "credentials")
    private String creadentials;

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCreadentials() {
        return creadentials;
    }

    public void setCreadentials(String creadentials) {
        this.creadentials = creadentials;
    }
}