/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.area;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaRecursive.java, v 0.1 2024‐10‐07 11:13 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreAreaRecursive {

    private CoreArea currentArea;
    private CoreAreaRecursive parentAreaRecursive;

    public CoreAreaRecursive(CoreArea currentArea) {
        this.currentArea = currentArea;
    }

    public void setParentAreaRecursive(CoreAreaRecursive parentAreaRecursive) {
        this.parentAreaRecursive = parentAreaRecursive;
    }

    public CoreArea getCurrentArea() {
        return currentArea;
    }

    public CoreAreaRecursive getParentAreaRecursive() {
        return parentAreaRecursive;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}