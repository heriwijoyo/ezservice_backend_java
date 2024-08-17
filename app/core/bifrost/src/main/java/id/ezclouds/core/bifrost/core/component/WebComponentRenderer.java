/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.component;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.bifrost.app.webapp.WebAppPage;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebComponentRenderer.java, v 0.1 2024‐08‐17 9:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class WebComponentRenderer {

    private static final String SEARCH_SCENE_PHONE = "MEMBER_PHONE=Search By Phone";
    private static final String SEARCH_SCENE_NAME = "MEMBER_NAME=Search By Name";
    private static final String SEARCH_SCENE_CODE = "CODE=Search By Code";

    public static String getListSearchComponent(WebAppPage webAppPage) {

        switch (webAppPage) {
            case MEMBERS:
                return composeSearchComponentHtml(SEARCH_SCENE_PHONE, SEARCH_SCENE_NAME);
            case COMMON_TABLES:
                return composeSearchComponentHtml(SEARCH_SCENE_CODE);

            default:
                return StringUtil.EMPTY;
        }
    }

    private static String composeSearchComponentHtml(String... scenes) {
        if (scenes.length < 1) {
            return StringUtil.EMPTY;
        }

        StringBuilder componentSb = new StringBuilder();
        componentSb.append("<table class=\"table\">");
        componentSb.append("<tr><td><div class=\"form-line\">");

        componentSb.append("<select class=\"form-control show-tick\" id=\"searchScene\">");
        for (String sceneStr : scenes) {
            String[] sceneSplit = sceneStr.split("=");
            componentSb.append("<option value=\""+ sceneSplit[0] +"\">"+ sceneSplit[1] +"</option>");
        }
        componentSb.append("</select></div></td>");

        componentSb.append("<td><div class=\"form-group form-float\"><div class=\"form-line\"><input type=\"text\" class=\"form-control\" id=\"searchKeyword\" placeholder=\"Keyword...\" required></div></div></td>");
        componentSb.append("<td width=\"1%\"><button id=\"btnSearch\" type=\"submit\" class=\"btn bg-blue btn-circle waves-effect waves-circle waves-float\"><i class=\"material-icons\">search</i></button></td>");
        componentSb.append("<td width=\"1%\"><button id=\"btnSearchReset\" type=\"button\" class=\"button button-rounded waves-effect waves-float\">RESET</button></td>");
        componentSb.append("</tr></table>");

        return componentSb.toString();
    }
}