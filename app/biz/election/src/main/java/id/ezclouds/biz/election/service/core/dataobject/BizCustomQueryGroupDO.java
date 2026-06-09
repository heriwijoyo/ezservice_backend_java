/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.core.dataobject;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCustomQueryGroupDO.java, v 0.1 2024‐07‐15 2:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizCustomQueryGroupDO {

    private String groupName;
    private long count1Value;
    private long count2Value;
    private long count3Value;
    private long count4Value;
    private long count5Value;

    public BizCustomQueryGroupDO(String groupName, long count1Value) {
        this(groupName, count1Value, 0, 0, 0, 0);
    }

    public BizCustomQueryGroupDO(String groupName, long count1Value, long count2Value) {
        this(groupName, count1Value, count2Value, 0, 0, 0);
    }

    public BizCustomQueryGroupDO(String groupName, long count1Value, long count2Value, long count3Value) {
        this(groupName, count1Value, count2Value, count3Value, 0, 0);
    }

    public BizCustomQueryGroupDO(String groupName, long count1Value, long count2Value, long count3Value, long count4Value) {
        this(groupName, count1Value, count2Value, count3Value, count4Value, 0);
    }

    public BizCustomQueryGroupDO(String groupName, long count1Value, long count2Value, long count3Value, long count4Value, long count5Value) {
        this.groupName = groupName;
        this.count1Value = count1Value;
        this.count2Value = count2Value;
        this.count3Value = count3Value;
        this.count4Value = count4Value;
        this.count5Value = count5Value;
    }

    public String getGroupName() {
        return groupName;
    }

    public long getCount1Value() {
        return count1Value;
    }

    public long getCount2Value() {
        return count2Value;
    }

    public long getCount3Value() {
        return count3Value;
    }

    public long getCount4Value() {
        return count4Value;
    }

    public long getCount5Value() {
        return count5Value;
    }
}