/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.election.service.apibiz.admin.BizSuperAdminService;
import id.ezclouds.common.facade.process.AsyncProcessExecutor;
import id.ezclouds.common.facade.process.SyncProcessExecutor;
import id.ezclouds.common.model.process.ProcessMode;
import id.ezclouds.common.model.process.ProcessName;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.process.SchedulerProcessor;
import id.ezclouds.common.model.result.BaseResult;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.core.bifrost.app.api.event.SuperAdminEvent;
import id.ezclouds.common.util.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Autowired
    private SyncProcessExecutor syncProcessExecutor;

    @Autowired
    private AsyncProcessExecutor asyncProcessExecutor;

    /**
    @Autowired
    private BizSchedulerServiceImpl bizSchedulerServiceImpl;
     */

    @GetMapping(value = "/api/local/auth.json")
    private void localSuperUserAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String localAddr = request.getLocalAddr();

        if (!"127.0.0.1".equals(localAddr)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        else {
            EzAppContextHolder.init(SuperAdminEvent.SU_CREATE_WEB_SESSION);
            BizResult bizResult = bizSuperAdminService.createSuperAdminSession(true);

            response.setStatus(HttpStatus.OK.value());
            if (bizResult.isSuccess()) {
                response.getWriter().write((String)bizResult.getObject());
            } else {
                response.getWriter().write(bizResult.getErrorMessage());
            }
            response.getWriter().flush();
        }
    }

    @GetMapping(value = "/api/local/authPublic/{orgCode}")
    private void localSuperUserCreatePublicSession(@PathVariable("orgCode") String orgCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String localAddr = request.getLocalAddr();

        if (!"127.0.0.1".equals(localAddr)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        else {
            EzAppContextHolder.init(SuperAdminEvent.SU_CREATE_PUBLIC_SESSION);
            BizResult bizResult = bizSuperAdminService.createSuperAdminPublicSession(orgCode);

            response.setStatus(HttpStatus.OK.value());
            if (bizResult.isSuccess()) {
                response.getWriter().write((String)bizResult.getObject());
            } else {
                response.getWriter().write(bizResult.getErrorMessage());
            }
            response.getWriter().flush();
        }
    }

    @GetMapping(value = "/api/local/scheduler/{scene}/{param}")
    private void localScheduleHandler(
            @PathVariable("scene") String scene,
            @PathVariable("param") String param,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String localAddr = request.getLocalAddr();

        if (!"127.0.0.1".equals(localAddr)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        else {
            SchedulerProcessor schedulerProcessor = BeanFacadeUtil.getBean(SchedulerProcessor.class);
            BaseResult result = schedulerProcessor.execute(scene, param);

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(result.getResultCode());
            response.getWriter().flush();
        }
    }

    @GetMapping(value = "/api/local/processExecutor/{mode}/{name}/{param}")
    private void processExecutor(
            @PathVariable("mode") String mode,
            @PathVariable("name") String name,
            @PathVariable(value = "param", required = false) String param,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String localAddr = request.getLocalAddr();
        if (!"127.0.0.1".equals(localAddr)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            ProcessMode processMode = ProcessMode.getByCode(mode);
            ProcessName processName = ProcessName.getByCode(name);

            switch (processMode) {
                case SYNC:
                    syncProcessExecutor.execute(processName, param);
                    break;
                case ASYNC:
                    asyncProcessExecutor.execute(processName, param);
                    break;
            }

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write("process execute in mode: "+ processMode.getCode());
            response.getWriter().flush();
        }
    }
}