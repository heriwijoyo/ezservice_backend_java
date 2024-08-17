/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.component;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebFormField.java, v 0.1 2024‐08‐17 10:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebFormField {

    private String fieldId;
    private String label;
    private WebFormFieldType fieldType;
    private WebFormOptionDSType optionDSType;
    private List<WebFormOption> staticOptions;
    private String optionDSRemoteUrl;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public WebFormFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(WebFormFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public WebFormOptionDSType getOptionDSType() {
        return optionDSType;
    }

    public void setOptionDSType(WebFormOptionDSType optionDSType) {
        this.optionDSType = optionDSType;
    }

    public List<WebFormOption> getStaticOptions() {
        return staticOptions;
    }

    public void setStaticOptions(List<WebFormOption> staticOptions) {
        this.staticOptions = staticOptions;
    }

    public String getOptionDSRemoteUrl() {
        return optionDSRemoteUrl;
    }

    public void setOptionDSRemoteUrl(String optionDSRemoteUrl) {
        this.optionDSRemoteUrl = optionDSRemoteUrl;
    }
}