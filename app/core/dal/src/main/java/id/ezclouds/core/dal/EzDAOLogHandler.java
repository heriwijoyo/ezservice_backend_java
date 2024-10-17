/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal;

import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.common.util.exception.ExceptionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzDAOLogHandler.java, v 0.1 2024‐07‐26 4:30 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Aspect
@Component
public class EzDAOLogHandler {

    @Before("@annotation(id.ezclouds.common.model.annotation.EzDAOLogger)")
    public void before(JoinPoint joinPoint) {
        String traceId = "_";
        String orgId = "ORG_ID";
        if (EzAppContextHolder.getContext() != null) {
            traceId = EzAppContextHolder.getContext().getTraceId();
            orgId = EzAppContextHolder.getContext().getOrgId();
        }
        EzDAOProfiler.start(traceId, orgId, getInvokeTarget(joinPoint));
    }

    @AfterReturning(value = "@annotation(id.ezclouds.common.model.annotation.EzDAOLogger)", returning = "retValue")
    public void after(JoinPoint joinPoint, Object retValue) {
        String resultValue = StringUtil.EMPTY;
        if (retValue instanceof String) {
            resultValue = (String) retValue;
        }
        if (retValue instanceof Long) {
            resultValue = ""+ retValue;
        }
        if (retValue instanceof List) {
            resultValue = ""+ ((List)retValue).size();
        }
        if (retValue instanceof PageResult) {
            resultValue = ""+ ((PageResult)retValue).getNumberRecord();
        }
        EzDAOProfiler.end(getInvokeTarget(joinPoint), "Y", resultValue);
    }

    @AfterThrowing(value = "@annotation(id.ezclouds.common.model.annotation.EzDAOLogger)", throwing = "e")
    public void error(JoinPoint joinPoint, Exception e) {
        EzDAOProfiler.end(getInvokeTarget(joinPoint), "E", ExceptionUtil.getErrorContext(e));
    }

    private String getInvokeTarget(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName() +"."+ joinPoint.getSignature().getName();
    }
}