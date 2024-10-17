/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.election.BizVoterInvalid;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: RecoverBizVoterAccumulateArea.java, v 0.1 2024‐10‐12 6:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class RecoverBizVoterAccumulateArea {

    private String processId;
    private EzCoreTopic ezCoreTopic;
    private BizVoter bizVoter;
    private BizVoterInvalid bizVoterInvalid;

    public RecoverBizVoterAccumulateArea(String processId, EzCoreTopic ezCoreTopic) {
        this.processId = processId;
        this.ezCoreTopic = ezCoreTopic;
    }

    public void setBizVoter(BizVoter bizVoter) {
        this.bizVoter = bizVoter;
    }

    public BizVoterInvalid getBizVoterInvalid() {
        return bizVoterInvalid;
    }

    public void setBizVoterInvalid(BizVoterInvalid bizVoterInvalid) {
        this.bizVoterInvalid = bizVoterInvalid;
    }

    public String getProcessId() {
        return processId;
    }

    public EzCoreTopic getEzCoreTopic() {
        return ezCoreTopic;
    }

    public BizVoter getBizVoter() {
        return bizVoter;
    }
}