/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.biz.ezservice.service.apibiz.admin.BizAdminService;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.digestlog.EmptyDigestLog;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.shared.context.EzAppContext;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AdminWebController.java, v 0.1 2024‐02‐10 8:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class AdminWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.ADMIN_WEB_CONTROLLER);

    @Autowired
    private BizAdminService bizAdminService;

    @GetMapping(value = "/webapp")
    private void adminHome(@RequestParam(name = "sid", required = false) String sid, HttpServletResponse response) throws IOException {
        response.sendRedirect("/webapp/login.htm");
    }

    @PostMapping(value = "/webapp/login.json", consumes = {MediaType.ALL_VALUE})
    @ResponseBody
    private String adminLogin(@RequestParam(name = "sessionCode", required = false) String sessionCode, HttpServletResponse response) {

        EzAppContextHolder.init(WebEvent.WEB_LOGIN_BY_SESSION_CODE);
        BizResult bizResult = bizAdminService.loginWebSession(sessionCode);
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(bizResult));

        if (bizResult.isSuccess()) {
            return (String) bizResult.getObject();
        } else {
            response.setStatus(404);
        }
        return EzErrorCode.SESSION_CODE_INVALID.getCode();
    }

    private DigestLog getDigestLog(BizResult bizResult) {
        return new EmptyDigestLog(bizResult.isSuccess(), getResultCode(bizResult));
    }

    private String getResultCode(BizResult bizResult) {
        if (bizResult.isSuccess()) {
            return "RESULT_SUCCESS";
        }
        if (bizResult.getErrorCode() != null) {
            return bizResult.getErrorCode().getCode();
        }
        return "UNKNOWN_RESULT";
    }
}