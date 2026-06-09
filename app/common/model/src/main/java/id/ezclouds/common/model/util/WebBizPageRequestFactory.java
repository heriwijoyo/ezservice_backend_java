/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import id.ezclouds.common.model.request.WebBizPageRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizPageRequestFactory.java, v 0.1 2024‐08‐18 12:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class WebBizPageRequestFactory {

    public static WebBizPageRequest createRequest(String sessionId, int pageNumber, int pageSize, String searchScene, String searchKeyword) {
        WebBizPageRequest request = new WebBizPageRequest();
        request.setSessionId(sessionId);
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        request.setSearchScene(searchScene);
        request.setSearchKeyword(searchKeyword);
        return request;
    }
}