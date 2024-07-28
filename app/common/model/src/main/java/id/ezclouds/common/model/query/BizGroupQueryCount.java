/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.query;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizGroupQueryCount.java, v 0.1 2024‐07‐28 6:07 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizGroupQueryCount {

    private String groupValue;
    private long countValue;
    private long count2Value;

    public BizGroupQueryCount(String groupValue, long countValue, long count2Value) {
        this.groupValue = groupValue;
        this.countValue = countValue;
        this.count2Value = count2Value;
    }

    public BizGroupQueryCount(String groupValue, long countValue) {
        this(groupValue, countValue, 0);
    }

    public String getGroupValue() {
        return groupValue;
    }

    public long getCountValue() {
        return countValue;
    }

    public long getCount2Value() {
        return count2Value;
    }
}