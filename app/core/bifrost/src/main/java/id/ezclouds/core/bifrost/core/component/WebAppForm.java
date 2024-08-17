/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.component;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppForm.java, v 0.1 2024‐08‐17 10:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebAppForm {

    private String title;
    private List<WebFormField> fields;
    private List<WebFormButton> topButtons;
    private List<WebFormButton> bottomButtons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<WebFormField> getFields() {
        return fields;
    }

    public void setFields(List<WebFormField> fields) {
        this.fields = fields;
    }

    public List<WebFormButton> getTopButtons() {
        return topButtons;
    }

    public void setTopButtons(List<WebFormButton> topButtons) {
        this.topButtons = topButtons;
    }

    public List<WebFormButton> getBottomButtons() {
        return bottomButtons;
    }

    public void setBottomButtons(List<WebFormButton> bottomButtons) {
        this.bottomButtons = bottomButtons;
    }
}