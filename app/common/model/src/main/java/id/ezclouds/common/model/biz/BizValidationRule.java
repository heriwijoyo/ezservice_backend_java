/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizValidationRule.java, v 0.1 2024‐10‐01 1:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizValidationRule {

    NOT_BLANK("NOT_BLANK"),
    LENGTH_MIN("LENGTH_MIN"),
    LENGTH_MAX("LENGTH_MAX"),
    NUMBER_MIN("NUMBER_MIN"),
    NUMBER_MAX("NUMBER_MAX"),
    UNKNOWN("UNKNOWN"),

    ;

    private final String code;
    private String field;
    private Object param;
    private String invalidMessage;

    BizValidationRule(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public int getParamInt() {
        if (param == null) {
            return 0;
        }
        String paramStr = (String) param;
        try {
            return Integer.parseInt(paramStr);
        } catch (Exception ignored) {
            return 0;
        }
    }

    public String getInvalidMessage() {
        return invalidMessage;
    }

    public void setInvalidMessage(String invalidMessage) {
        this.invalidMessage = invalidMessage;
    }

    public static BizValidationRule getByCode(String code) {
        for (BizValidationRule rule : values()) {
            if (rule.code.equals(code)) {
                return rule;
            }
        }
        return UNKNOWN;
    }
}