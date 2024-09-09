/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.context.EzAppContext;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.LogUtil;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.core.web.model.WebPageRequest;
import id.ezclouds.core.bifrost.core.web.model.WebPageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageControllerTemplate.java, v 0.1 2024‐08‐22 11:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class WebPageControllerTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.WEB_PAGES_CONTROLLER);

    public static void execute(WebPageRequest request, Handler handler) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE);
        WebPageResult result = new WebPageResult();

        try {
            handler.checkRequest(request);
            String htmlContent = handler.processWebContent(request);

            handler.getServletResponse().getWriter().write(htmlContent);
            handler.getServletResponse().setStatus(HttpStatus.OK.value());
            handler.getServletResponse().getWriter().flush();
            result.setSuccess(true);

        } catch (Exception e) {
            if (e instanceof IOException) {
                result.setErrorCode(EzErrorCode.WEB_PAGE_ERROR);
            } else if (e instanceof EzErrorException) {
                EzErrorException ezErrorException = (EzErrorException) e;
                result.setErrorCode(ezErrorException.getEzErrorCode());
            } else {
                result.setErrorCode(EzErrorCode.SYSTEM_ERROR);
            }
            handler.getServletResponse().setStatus(HttpStatus.NOT_FOUND.value());

        } finally {
            EzAppContext context = EzAppContextHolder.getContext();
            String pathLog = StringUtil.isNotBlank(request.getPath()) ? request.getPath() : "PATH_EMPTY";

            String sectionLog = StringUtil.isNotBlank(request.getSection()) ? request.getSection() : "SECTION_EMPTY";

            LogUtil.info(
                    LOGGER,
                    context.getTraceId(),
                    context.getEzAppEvent().getEventCode(),
                    result.getResultCode(),
                    context.getTimeCost(),
                    pathLog,
                    sectionLog,
                    StringUtil.isNotBlank(request.getPageId()) ? request.getPageId() : "PAGE_ID_EMPTY",
                    request.getSessionId()
            );
        }
    }

    public interface Handler {
        void checkRequest(WebPageRequest request);
        String processWebContent(WebPageRequest request) throws Exception;
        HttpServletResponse getServletResponse();
    }
}