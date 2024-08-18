/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebFormButton.java, v 0.1 2024‐08‐17 10:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebFormButton {

    private WebFormButtonType buttonType;
    private String btnId;
    private String label;

    public WebFormButtonType getButtonType() {
        return buttonType;
    }

    public void setButtonType(WebFormButtonType buttonType) {
        this.buttonType = buttonType;
    }

    public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String btnId) {
        this.btnId = btnId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}