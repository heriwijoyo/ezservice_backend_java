/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.util;

import id.ezclouds.common.util.HashUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSeqUtil.java, v 0.1 2024‐09‐28 2:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class CoreSeqUtil {

    public static String composeBizSeqId(String orgId, String scene, String sceneId) {
        return HashUtil.createHash(orgId, scene, sceneId);
    }

    public static String composeSeqCode(int sequence, int sequenceLength) {
        String seqStr = String.valueOf(sequence);
        while (seqStr.length() < sequenceLength) {
            seqStr = "0" + seqStr;
        }
        return seqStr;
    }
}