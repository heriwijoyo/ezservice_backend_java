/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WhatsappResendRequest.java, v 0.1 2024‐05‐18 3:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WhatsappResendRequest extends ConnectRequest {

    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}