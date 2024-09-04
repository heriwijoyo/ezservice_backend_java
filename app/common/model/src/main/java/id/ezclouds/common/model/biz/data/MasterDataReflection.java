/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MasterDataReflection.java, v 0.1 2024‐09‐04 6:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface MasterDataReflection {

    void setDataId(String dataId);
    void setDataName(String dataName);
    void setNumberValue1(int value);
    void setNumberValue2(int value);
    void setNumberValue3(int value);
    void setNumberValue4(int value);
    void setNumberValue5(int value);
    void setCharValue1(String value);
    void setCharValue2(String value);
    void setCharValue3(String value);
    void setCharValue4(String value);
    void setCharValue5(String value);

    String getDataId();
    String getDataName();
    Integer getNumberValue1();
    Integer getNumberValue2();
    Integer getNumberValue3();
    Integer getNumberValue4();
    Integer getNumberValue5();
    String getCharValue1();
    String getCharValue2();
    String getCharValue3();
    String getCharValue4();
    String getCharValue5();
}