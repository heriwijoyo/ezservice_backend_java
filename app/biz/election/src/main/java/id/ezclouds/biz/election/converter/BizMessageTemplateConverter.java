/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.converter;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMessageTemplateConverter.java, v 0.1 2024‐02‐05 3:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMessageTemplateConverter {

    public static String getMessage(String template, Map<String, String> values) {
        if (template == null || values == null) {
            return template;
        }

        for (Map.Entry<String, String> entry : values.entrySet()) {
            template = template.replace("{"+ entry.getKey() +"}", entry.getValue());
        }
        return template;
    }

    public static String getExpiryLabelMin(int mins) {
        return mins + " menit kedepan";
    }
}