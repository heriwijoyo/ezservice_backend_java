/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.data;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebViewDataProvider.java, v 0.1 2024‐08‐25 3:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface WebViewDataProvider {

    Map<String, String> getViewData(String request);
}