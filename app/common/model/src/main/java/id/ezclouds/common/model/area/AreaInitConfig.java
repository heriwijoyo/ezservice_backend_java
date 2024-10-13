/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.area;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AreaInitConfig.java, v 0.1 2024‐09‐16 8:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AreaInitConfig {

    private CoreAreaLevel targetLevel;
    private List<CoreArea> rootAreas = new ArrayList<>();

    public CoreAreaLevel getTargetLevel() {
        return targetLevel;
    }

    public void setTargetLevel(CoreAreaLevel targetLevel) {
        this.targetLevel = targetLevel;
    }

    public List<CoreArea> getRootAreas() {
        return rootAreas;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}