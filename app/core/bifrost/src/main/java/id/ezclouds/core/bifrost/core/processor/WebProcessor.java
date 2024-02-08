/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.core.bifrost.app.web.event.WebEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebProcessor.java, v 0.1 2024‐02‐08 8:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface WebProcessor {

    Object process(WebEvent event, Object request, HttpServletResponse servletResponse) throws Exception;
}