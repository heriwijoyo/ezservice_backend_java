/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.innerprocess.WebBizInnerProcessor;
import id.ezclouds.core.bifrost.core.processor.WebProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizProcessor.java, v 0.1 2024‐02‐08 8:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class WebBizProcessor implements WebProcessor {

    @Autowired
    private WebBizInnerProcessor innerProcessor;

    @Override
    public Object process(WebEvent event, Object request, HttpServletResponse servletResponse) throws Exception {
        switch (event) {
            case GET_IMAGE_PUBLIC:
            case GET_IMAGE_PRIVATE:
                innerProcessor.loadCommonImage(request, servletResponse);
                break;

        }
        return null;
    }
}