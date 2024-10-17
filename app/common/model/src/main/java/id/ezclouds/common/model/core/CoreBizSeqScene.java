/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizSeqScene.java, v 0.1 2024‐09‐28 1:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreBizSeqScene {
    String getCode();
    int seqLength();

    String getOrgId();
    String getSceneId();
}