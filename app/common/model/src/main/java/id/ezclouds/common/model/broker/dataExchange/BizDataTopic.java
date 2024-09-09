/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.broker.dataExchange;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizDataTopic.java, v 0.1 2024‐09‐09 12:03 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizDataTopic {

    BIZ_REPORT_OVERALL("BIZ_REPORT_OVERALL")

    ;
    private final String code;

    BizDataTopic(String code) {
        this.code = code;
    }
}