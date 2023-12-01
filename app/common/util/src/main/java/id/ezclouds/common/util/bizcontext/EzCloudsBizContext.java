/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.bizcontext;

import id.ezclouds.common.util.eventcode.EventCodeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCloudsBizContext.java, v 0.1 2023‐06‐19 3:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzCloudsBizContext implements Serializable {

    private static final long serialVersionUID = -1998673012469565127L;

    private Map<String, EzBizContext> ezBizContextMap = new HashMap<>();

    private EventCodeEnum eventCodeEnum;

    /**
     * Getter method for property <tt>eventCodeEnum</tt>.
     *
     * @return property value of eventCodeEnum
     */
    public EventCodeEnum getEventCodeEnum() {
        return eventCodeEnum;
    }

    /**
     * Setter method for property <tt>eventCodeEnum</tt>.
     *
     * @param eventCodeEnum value to be assigned to property eventCodeEnum
     */
    public void setEventCodeEnum(EventCodeEnum eventCodeEnum) {
        this.eventCodeEnum = eventCodeEnum;
    }
}