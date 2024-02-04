/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service.client.request;

import id.ezclouds.core.integration.service.client.config.WatzapConfig;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WatzapSendRequest.java, v 0.1 2024‐02‐05 2:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WatzapSendRequest {

    private String api_key;// = WatzapConfig.Credential.API_KEY;
    private String number_key;// = WatzapConfig.Credential.NUMBER_KEY;
    private String phone_no;
    private String message;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getNumber_key() {
        return number_key;
    }

    public void setNumber_key(String number_key) {
        this.number_key = number_key;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}