/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.ezservice.service.apibiz.admin.BizSuperAdminService;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.core.bifrost.app.api.event.SuperAdminEvent;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: LocalController.java, v 0.1 2024‐03‐31 1:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class LocalController {

    @Autowired
    private BizSuperAdminService bizSuperAdminService;

    @GetMapping(value = "/api/local/auth.json")
    private void localSuperUserAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String localAddr = request.getLocalAddr();

        if (!"127.0.0.1".equals(localAddr)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        else {
            EzAppContextHolder.init(SuperAdminEvent.SU_CREATE_WEB_SESSION);
            BizResult bizResult = bizSuperAdminService.createSuperAdminSession();

            response.setStatus(HttpStatus.OK.value());
            if (bizResult.isSuccess()) {
                response.getWriter().write((String)bizResult.getObject());
            } else {
                response.getWriter().write(bizResult.getErrorMessage());
            }
            response.getWriter().flush();
        }
    }
}