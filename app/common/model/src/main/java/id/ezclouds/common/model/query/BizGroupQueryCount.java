/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.query;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizGroupQueryCount.java, v 0.1 2024‐07‐28 6:07 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizGroupQueryCount {

    private String groupId;
    private String groupLabel;
    private long groupCount;

    public BizGroupQueryCount(String groupId, long groupCount) {
        this.groupId = groupId;
        this.groupCount = groupCount;
    }

    public BizGroupQueryCount(String groupId, String groupLabel, long groupCount) {
        this.groupId = groupId;
        this.groupLabel = groupLabel;
        this.groupCount = groupCount;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupLabel() {
        return groupLabel;
    }

    public long getGroupCount() {
        return groupCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}