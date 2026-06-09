/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ShardUtil.java, v 0.1 2024‐01‐29 3:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ShardUtil {

    public static String getShardId(String memberId) {
        return memberId.substring(3, 5);
    }
}