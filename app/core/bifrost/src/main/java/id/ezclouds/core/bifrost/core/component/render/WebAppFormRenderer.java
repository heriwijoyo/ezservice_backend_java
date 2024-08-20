/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.component.render;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.bifrost.core.component.*;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppFormRenderer.java, v 0.1 2024‐08‐17 12:34 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class WebAppFormRenderer {

    public static String renderForm(WebAppForm webAppForm) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<div class=\"card corner-radius\">");
        htmlBuilder.append("<div class=\"header\"><h2>"+ webAppForm.getTitle() +"</h2></div>");
        htmlBuilder.append("<div class=\"body\"><div class=\"row clearfix\">");

        for (WebFormField field : webAppForm.getFields()) {
            htmlBuilder.append("<div class=\"form-group col-sm-12\">");
            htmlBuilder.append("<div class=\"form-line\">");
            htmlBuilder.append("<div class=\"font-12\">"+ field.getLabel() +"</div>");

            htmlBuilder.append(renderField(field));

            htmlBuilder.append("</div></div>");
        }
        htmlBuilder.append(renderBottomButtons(webAppForm.getBottomButtons()));

        htmlBuilder.append("</div></div></div>");
        return htmlBuilder.toString();
    }

    public static String renderSmartFormClient(WebAppForm webAppForm) {
        StringBuilder smartFormClientSb = new StringBuilder();
        smartFormClientSb.append("<script type=\"text/javascript\" src=\"assets/js/ezsmart-form-client.js\"></script>");
        smartFormClientSb.append("<script type=\"text/javascript\"> var EzSmartFormClientStarter = { start: function() {");

        for (WebFormField field : webAppForm.getFields()) {
            if (field.getFieldType() == WebFormFieldType.SELECT && field.getOptionDSType() == WebFormOptionDSType.REMOTE) {
                smartFormClientSb.append("EzSmartFormClient.fetchAndParseRemoteOption('"+ field.getFieldId() +"', '"+ field.getOptionDSRemoteUrl() +"');");
            }
        }

        for (WebFormButton button : webAppForm.getBottomButtons()) {
            if (button.getButtonType() == WebFormButtonType.SUBMIT_FORM) {
                smartFormClientSb.append("$('#"+ button.getBtnId() +"').click(function(){EzDashboardBizService.submitForm();});");
            }
        }

        smartFormClientSb.append("}};</script>");
        return smartFormClientSb.toString();
    }

    public static String renderSmartFormClientDetail(WebAppForm webAppForm) {
        StringBuilder smartFormClientSb = new StringBuilder();
        smartFormClientSb.append("<script type=\"text/javascript\" src=\"assets/js/ezsmart-form-client.js\"></script>");
        smartFormClientSb.append("<script type=\"text/javascript\"> var EzSmartFormClientStarter = { pageDefault: '"+ webAppForm.getPageDefault() +"', startDetail: function() {");
        smartFormClientSb.append("EzSmartFormClient.validateDetailId();");

        for (WebFormField field : webAppForm.getFields()) {
            if (field.getFieldType() == WebFormFieldType.SELECT && field.getOptionDSType() == WebFormOptionDSType.REMOTE) {
                smartFormClientSb.append("EzSmartFormClient.fetchAndStoreRemoteOption('"+ field.getFieldId() +"', '"+ field.getOptionDSRemoteUrl() +"');");
            }
        }

        for (WebFormButton button : webAppForm.getBottomButtons()) {
            if (button.getButtonType() == WebFormButtonType.SUBMIT_FORM) {
                smartFormClientSb.append("$('#"+ button.getBtnId() +"').click(function(){EzDashboardBizService.submitForm();});");
            }
        }

        smartFormClientSb.append("EzDashboardBizService.fetchData(EzSmartFormClient.detailId);");
        smartFormClientSb.append("}};</script>");
        return smartFormClientSb.toString();
    }

    private static String renderField(WebFormField field) {
        switch (field.getFieldType()) {
            case INPUT_TEXT:
                return "<input type=\"text\" class=\"form-control\" id=\""+ field.getFieldId() +"\" placeholder=\""+ field.getLabel() +"\">";
            case TEXT_AREA:
                return "<div class=\"form-group\"><textarea class=\"form-control\" id=\""+ field.getFieldId() +"\" class=\"form-control\" cols=\"60\" rows=\"10\" required></textarea></div>";
            case SELECT:
                return renderSelectField(field);

            default:
                return StringUtil.EMPTY;
        }
    }

    private static String renderSelectField(WebFormField field) {
        switch (field.getOptionDSType()) {
            case STATIC:
            case REMOTE:
                return "<select class=\"form-control show-tick\" id=\""+ field.getFieldId() +"\" data-remote-url=\""+ field.getOptionDSRemoteUrl() +"\"></select>";
            default:
                return StringUtil.EMPTY;
        }
    }

    private static String renderBottomButtons(List<WebFormButton> buttons) {
        if (buttons.size() < 1) {
            return StringUtil.EMPTY;
        }

        StringBuilder btnGroupHtmlSb = new StringBuilder();
        btnGroupHtmlSb.append("<div class=\"col-sm-12\">");
        for (WebFormButton button : buttons) {
            btnGroupHtmlSb.append("<button id=\""+ button.getBtnId() +"\" class=\"button button-rounded waves-effect waves-float pull-right\" type=\"submit\">"+ button.getLabel() +"</button>");
        }

        btnGroupHtmlSb.append("</div>");
        return btnGroupHtmlSb.toString();
    }
}