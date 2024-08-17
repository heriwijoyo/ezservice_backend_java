/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.component.factory;


import id.ezclouds.core.bifrost.core.component.WebFormButton;
import id.ezclouds.core.bifrost.core.component.WebFormField;
import id.ezclouds.core.bifrost.core.component.WebFormFieldType;
import id.ezclouds.core.bifrost.core.component.WebFormOptionDSType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CommonTableFormFactory.java, v 0.1 2024‐08‐17 11:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CommonTableFormFactory extends CommonWebAppFormFactory {

    @Override
    protected String getTitle() {
        return "CREATE NEW TABLE";
    }

    @Override
    protected List<WebFormField> composeFields() {
        List<WebFormField> fields = new ArrayList<>();

        WebFormField selectOrgField = new WebFormField();
        selectOrgField.setFieldId("selectOrgId");
        selectOrgField.setLabel("Select Organization");
        selectOrgField.setFieldType(WebFormFieldType.SELECT);
        selectOrgField.setOptionDSType(WebFormOptionDSType.REMOTE);
        selectOrgField.setOptionDSRemoteUrl("organizations.json");

        WebFormField inputCodeField = new WebFormField();
        inputCodeField.setFieldId("tableCode");
        inputCodeField.setFieldType(WebFormFieldType.INPUT_TEXT);
        inputCodeField.setLabel("Table Code");
        fields.add(inputCodeField);

        return fields;
    }

    @Override
    protected List<WebFormButton> composeTopButtons() {
        return null;
    }

    @Override
    protected List<WebFormButton> composeBottomButtons() {
        List<WebFormButton> buttons = new ArrayList<>();
        WebFormButton submitBtn = new WebFormButton();
        submitBtn.setBtnId("formSubmitBtn");
        submitBtn.setLabel("SUBMIT");
        buttons.add(submitBtn);
        return buttons;
    }
}