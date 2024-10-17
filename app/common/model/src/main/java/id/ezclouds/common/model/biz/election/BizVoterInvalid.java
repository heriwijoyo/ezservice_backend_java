/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.election;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterInvalid.java, v 0.1 2024‐10‐06 10:16 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizVoterInvalid extends BizVoter {

    private String invalidCode;
    private String invalidMessage;

    public String getInvalidCode() {
        return invalidCode;
    }

    public void setInvalidCode(String invalidCode) {
        this.invalidCode = invalidCode;
    }

    public String getInvalidMessage() {
        return invalidMessage;
    }

    public void setInvalidMessage(String invalidMessage) {
        this.invalidMessage = invalidMessage;
    }
}