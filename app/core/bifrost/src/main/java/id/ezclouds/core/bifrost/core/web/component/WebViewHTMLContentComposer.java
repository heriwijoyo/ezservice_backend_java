/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.component;

import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.core.bifrost.core.web.model.WebPageContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebViewHTMLContentComposer.java, v 0.1 2024‐08‐25 3:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class WebViewHTMLContentComposer {

    @Autowired
    private BizCommonTableDAO bizCommonTableDAO;

    public String composeHTMLContent(String orgId, String pageId,  WebPageContentType contentType, String contentParam) {
        switch (contentType) {
            case COMMON_TABLES:
                return composeCommonTablesContent(orgId, pageId);
            default:
                return "";
        }
    }

    private String composeCommonTablesContent(String orgId, String pageId) {
        StringBuilder htmlContentSb = new StringBuilder();
        List<BizCommonTable> tables = bizCommonTableDAO.getByPageId(orgId, pageId);

        for (BizCommonTable commonTable : tables) {
            htmlContentSb.append("<div class=\"section\"><div class=\"section-content\">");
            htmlContentSb.append("<h3>");
            htmlContentSb.append(commonTable.getTitle());
            htmlContentSb.append("</h3>");
            htmlContentSb.append("<div id='");
            htmlContentSb.append(commonTable.getTableId());
            htmlContentSb.append("'></div>");

            htmlContentSb.append("<script>document.addEventListener('DOMContentLoaded',function(){");
            htmlContentSb.append("jspreadsheet(document.getElementById('"+ commonTable.getTableId() +"'),{");
            htmlContentSb.append("data:");
            htmlContentSb.append(commonTable.getData());
            htmlContentSb.append(",columns:");
            htmlContentSb.append(commonTable.getColumns());
            htmlContentSb.append(",csvFileName:'"+ commonTable.getTitle() +"',");
            htmlContentSb.append(commonTable.getConfig());
            htmlContentSb.append("});});</script>");

            htmlContentSb.append("</div></div>");
        }

        return htmlContentSb.toString();
    }
}