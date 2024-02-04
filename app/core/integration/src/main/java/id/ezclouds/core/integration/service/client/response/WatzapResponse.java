/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service.client.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WatzapResponse.java, v 0.1 2024‐02‐05 2:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WatzapResponse {

    private String status;
    private String message;
    private String ack;
    private String wa_number;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getWa_number() {
        return wa_number;
    }

    public void setWa_number(String wa_number) {
        this.wa_number = wa_number;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}