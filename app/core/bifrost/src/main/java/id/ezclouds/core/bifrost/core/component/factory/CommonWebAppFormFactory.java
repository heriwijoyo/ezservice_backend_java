/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.component.factory;

import id.ezclouds.core.bifrost.core.component.WebAppForm;
import id.ezclouds.core.bifrost.core.component.WebFormButton;
import id.ezclouds.core.bifrost.core.component.WebFormField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CommonWebAppFormFactory.java, v 0.1 2024‐08‐17 11:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class CommonWebAppFormFactory implements WebAppFormFactory {

    protected abstract String getPageDefault();
    protected abstract String getTitle();
    protected abstract List<WebFormField> composeFields();
    protected abstract List<WebFormButton> composeTopButtons();
    protected abstract List<WebFormButton> composeBottomButtons();

    private boolean isDetail = false;

    @Override
    public WebAppForm create() {
        WebAppForm webAppForm = new WebAppForm();
        webAppForm.setPageDefault(getPageDefault());
        webAppForm.setTitle(getTitle());

        if (composeFields() != null) {
            webAppForm.setFields(composeFields());
        } else {
            webAppForm.setFields(new ArrayList<>());
        }

        if (composeTopButtons() != null) {
            webAppForm.setTopButtons(composeTopButtons());
        } else {
            webAppForm.setTopButtons(new ArrayList<>());
        }

        if (composeBottomButtons() != null) {
            webAppForm.setBottomButtons(composeBottomButtons());
        } else {
            webAppForm.setBottomButtons(new ArrayList<>());
        }

        return webAppForm;
    }

    protected boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }
}