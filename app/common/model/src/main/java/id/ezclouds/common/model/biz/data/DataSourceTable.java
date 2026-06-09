/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DataSourceTable.java, v 0.1 2024‐09‐06 10:23 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum DataSourceTable {

    BIZ_MASTER_DATA("BIZ_MASTER_DATA", "VILLAGE_MASTER_DATA"),
    UNKNOWN("UNKNOWN", "UNKNOWN"),

    ;

    private final String code;
    private final String scene;

    DataSourceTable(String code, String scene) {
        this.code = code;
        this.scene = scene;
    }

    public static DataSourceTable getByCodeAndScene(String code, String scene) {
        for (DataSourceTable sourceTable : values()) {
            if (sourceTable.code.equals(code) && sourceTable.scene.equals(scene)) {
                return sourceTable;
            }
        }
        return UNKNOWN;
    }
}