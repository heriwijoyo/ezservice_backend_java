/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.component.factory;


import id.ezclouds.core.bifrost.core.web.component.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CommonTableFormFactory.java, v 0.1 2024‐08‐17 11:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CommonTableFormFactory extends CommonWebAppFormFactory {

    @Override
    protected String getPageDefault() {
        return "commonTables.htm";
    }

    @Override
    protected String getTitle() {
        return isDetail() ? "COMMON TABLE DETAIL" : "CREATE NEW TABLE";
    }

    @Override
    protected List<WebFormField> composeFields() {
        List<WebFormField> fields = new ArrayList<>();

        WebFormField selectOrgField = new WebFormField();
        selectOrgField.setFieldId("selectOrgId");
        selectOrgField.setLabel("Select Organization");
        selectOrgField.setFieldType(WebFormFieldType.SELECT);
        selectOrgField.setOptionDSType(WebFormOptionDSType.REMOTE);
        selectOrgField.setOptionDSRemoteUrl("option/organizations.json");
        fields.add(selectOrgField);

        WebFormField inputCodeField = new WebFormField();
        inputCodeField.setFieldId("tableCode");
        inputCodeField.setFieldType(WebFormFieldType.INPUT_TEXT);
        inputCodeField.setLabel("Table Code");
        fields.add(inputCodeField);

        WebFormField inputTitleField = new WebFormField();
        inputTitleField.setFieldId("tableTitle");
        inputTitleField.setFieldType(WebFormFieldType.INPUT_TEXT);
        inputTitleField.setLabel("Table Title");
        fields.add(inputTitleField);

        WebFormField textAreaColumnField = new WebFormField();
        textAreaColumnField.setFieldId("tableColumns");
        textAreaColumnField.setFieldType(WebFormFieldType.TEXT_AREA);
        textAreaColumnField.setLabel("Table Columns");
        fields.add(textAreaColumnField);

        WebFormField textAreaConfigField = new WebFormField();
        textAreaConfigField.setFieldId("tableConfig");
        textAreaConfigField.setFieldType(WebFormFieldType.TEXT_AREA);
        textAreaConfigField.setLabel("Table Config");
        fields.add(textAreaConfigField);

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
        submitBtn.setButtonType(WebFormButtonType.SUBMIT_FORM);
        submitBtn.setBtnId("formSubmitBtn");
        submitBtn.setLabel(isDetail()? "UPDATE" : "SUBMIT");
        buttons.add(submitBtn);
        return buttons;
    }
}