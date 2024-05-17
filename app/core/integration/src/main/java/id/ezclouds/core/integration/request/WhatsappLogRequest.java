/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.request;

import org.springframework.data.domain.PageRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WhatsappLogRequest.java, v 0.1 2024‐05‐16 10:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WhatsappLogRequest extends ConnectRequest {

    private String phone;
    private PageRequest pageRequest;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }
}