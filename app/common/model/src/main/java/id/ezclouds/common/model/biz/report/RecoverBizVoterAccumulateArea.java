/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

import id.ezclouds.common.model.biz.election.BizVoter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: RecoverBizVoterAccumulateArea.java, v 0.1 2024‐10‐12 6:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class RecoverBizVoterAccumulateArea {

    private String processId;
    private BizVoter bizVoter;

    public RecoverBizVoterAccumulateArea(String processId, BizVoter bizVoter) {
        this.processId = processId;
        this.bizVoter = bizVoter;
    }

    public String getProcessId() {
        return processId;
    }

    public BizVoter getBizVoter() {
        return bizVoter;
    }
}