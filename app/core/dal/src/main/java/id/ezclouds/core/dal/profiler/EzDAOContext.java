/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.profiler;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzDAOContext.java, v 0.1 2024‐07‐26 12:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzDAOContext {

    private long startTimeMillis;
    private long timeCost;
    private String traceId;
    private String orgId;
    private String invokeTarget;
    private String successCode;
    private String resultValue;

    public EzDAOContext() {
        startTimeMillis = System.currentTimeMillis();
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setInvokeTarget(String invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public void endTimeRecord() {
        timeCost = System.currentTimeMillis() - startTimeMillis;
    }

    public String getInvokeTarget() {
        return invokeTarget;
    }

    public String getLogMessage() {
        return "["+ traceId +"]["+
                orgId +","+
                invokeTarget +"," +
                timeCost +"ms,"+
                successCode +","+
                resultValue +"]";
    }
}