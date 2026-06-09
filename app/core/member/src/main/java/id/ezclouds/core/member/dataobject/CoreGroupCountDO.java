/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.dataobject;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreGroupCountDO.java, v 0.1 2024‐07‐18 6:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreGroupCountDO {

    private String groupValue;
    private Long groupCount;

    public CoreGroupCountDO(String groupValue, Long groupCount) {
        this.groupValue = groupValue;
        this.groupCount = groupCount;
    }

    public String getGroupValue() {
        return groupValue;
    }

    public Long getGroupCount() {
        return groupCount;
    }
}