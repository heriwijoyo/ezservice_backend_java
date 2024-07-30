/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.ezservice.service.apibiz.admin.BizSuperAdminService;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.common.facade.dal.EzSampleDAO;
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
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: LocalController.java, v 0.1 2024‐03‐31 1:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class LocalController {

    @Autowired
    private BizSuperAdminService bizSuperAdminService;

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

    /**
    @GetMapping(value = "/api/local/scheduler/{scene}")
    private void localSchedulerHandler(@PathVariable("scene") String scene, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String localAddr = request.getLocalAddr();

        if (!"127.0.0.1".equals(localAddr)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        else {
            BizResult bizResult = bizSchedulerServiceImpl.execute(scene);
            response.setStatus(HttpStatus.OK.value());

            String responseBody;
            if (bizResult.isSuccess()) {
                responseBody = "Y - SUCCESS";
            } else {
                responseBody = "N - "+ bizResult.getErrorCode();
            }
            response.getWriter().write(responseBody);
            response.getWriter().flush();
        }
    }*/

    @GetMapping(value = "/api/local/scheduler/{scene}")
    private void localScheduleHandler(@PathVariable("scene") String scene, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String localAddr = request.getLocalAddr();

        if (!"127.0.0.1".equals(localAddr)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        else {
            SchedulerProcessor schedulerProcessor = BeanFacadeUtil.getBean(SchedulerProcessor.class);
            BaseResult result = schedulerProcessor.execute(scene);

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(result.getResultCode());
            response.getWriter().flush();
        }
    }

    @GetMapping(value = "/api/local/trigger/sample")
    private void localTriggerSample(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String localAddr = request.getLocalAddr();

        if (!"127.0.0.1".equals(localAddr)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            EzSampleDAO ezSampleDAO = BeanFacadeUtil.getBean(EzSampleDAO.class);
            String sample = ezSampleDAO.getSample();
            Long count = ezSampleDAO.getSampleCount();
            List<String> list = ezSampleDAO.getSampleList();

            try {
                String ex = ezSampleDAO.getException();
            } catch (Exception ignored) {}

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(sample +","+ count +","+ list.size());
            response.getWriter().flush();
        }
    }
}