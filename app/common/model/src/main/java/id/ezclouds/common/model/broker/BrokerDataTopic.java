/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.broker;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerDataTopic.java, v 0.1 2024‐09‐09 12:03 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BrokerDataTopic {

    BIZ_REPORT_OVERALL("BIZ_REPORT_OVERALL")

    ;
    private final String code;

    BrokerDataTopic(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}