/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSeqScene.java, v 0.1 2024‐09‐22 5:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizSeqScene implements CoreSeqScene {

    CORE_MEMBER_ID("CORE_MEMBER_ID", "10"),
    BIZ_SUB_ORG("BIZ_SUB_ORG", "11"),
    BIZ_VOTER_ID("BIZ_VOTER_ID", "12"),

    ;

    private final String scene;
    private final String sceneCode;

    BizSeqScene(String scene, String sceneCode) {
        this.scene = scene;
        this.sceneCode = sceneCode;
    }

    @Override
    public String getScene() {
        return scene;
    }

    @Override
    public String getSceneCode() {
        return sceneCode;
    }

    public static BizSeqScene getByScene(String scene) {
        for (BizSeqScene seqScene : values()) {
            if (seqScene.scene.equals(scene)) {
                return seqScene;
            }
        }
        return null;
    }
}