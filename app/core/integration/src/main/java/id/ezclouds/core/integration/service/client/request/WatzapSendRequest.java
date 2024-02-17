/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service.client.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WatzapSendRequest.java, v 0.1 2024‐02‐05 2:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WatzapSendRequest {

    private String api_key;
    private String number_key;
    private String apiUri;
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

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
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