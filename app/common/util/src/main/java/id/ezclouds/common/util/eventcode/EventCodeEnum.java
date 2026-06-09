/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.eventcode;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EventCodeEnum.java, v 0.1 2023‐06‐19 1:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum EventCodeEnum {

    HTTP_API_INBOUND(ServiceCodeEnum.HEIMDALL_HTTP_ROUTE, "HTTP_API_INBOUND", "Inbound request from http call")

    ;

    private ServiceCodeEnum serviceCodeEnum;
    private String eventCode;
    private String description;

    EventCodeEnum(ServiceCodeEnum serviceCodeEnum, String eventCode, String description) {
        this.serviceCodeEnum = serviceCodeEnum;
        this.eventCode = eventCode;
        this.description = description;
    }

    /**
     * Getter method for property <tt>serviceCodeEnum</tt>.
     *
     * @return property value of serviceCodeEnum
     */
    public ServiceCodeEnum getServiceCodeEnum() {
        return serviceCodeEnum;
    }

    /**
     * Getter method for property <tt>eventCode</tt>.
     *
     * @return property value of eventCode
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }
}