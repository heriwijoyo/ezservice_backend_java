/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceScene.java, v 0.1 2024‐01‐01 2:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreSequenceScene {

    CORE_MEMBER_ID("CORE_MEMBER_ID", "10"),
    BIZ_SUB_ORG("BIZ_SUB_ORG", "11"),

    ;

    private final String code;
    private final String sceneCode;

    CoreSequenceScene(String code, String sceneCode) {
        this.code = code;
        this.sceneCode = sceneCode;
    }

    public String getCode() {
        return code;
    }

    public String getSceneCode() {
        return sceneCode;
    }
}