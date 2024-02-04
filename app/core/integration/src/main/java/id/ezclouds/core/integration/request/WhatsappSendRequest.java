/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WhatsappSendRequest.java, v 0.1 2024‐02‐05 1:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WhatsappSendRequest extends IntegrationRequest {

    private String phoneNumber;
    private String message;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}