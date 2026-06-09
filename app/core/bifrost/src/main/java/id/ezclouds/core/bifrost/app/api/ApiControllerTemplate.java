/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.common.model.request.api.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiPageRequest;
import id.ezclouds.common.model.result.api.BizApiPageResult;
import id.ezclouds.core.bifrost.core.SpringContextConfig;
import id.ezclouds.core.bifrost.core.processor.PreBizProcessor;
import id.ezclouds.common.model.util.ErrorResultUtil;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiControllerTemplate.java, v 0.1 2024‐05‐26 1:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ApiControllerTemplate {

    private static Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.API_CONTROLLER);

    public static <T> BizApiPageResult<T> execute(ApiEvent event, ApiPageRequest request, Handler<T> handler) {
        EzAppContextHolder.init(event);
        final BizApiPageResult<T> bizApiPageResult = new BizApiPageResult<>();

        try {
            AssertUtil.notNull(event, EzErrorCode.ILLEGAL_ACTION, "Illegal action request");
            AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "Request could not be null");

            PreBizProcessor preBizProcessor = SpringContextConfig.getBean(PreBizProcessor.class);
            preBizProcessor.process(event, request);

            ApiBizPageProcessor bizProcessor = SpringContextConfig.getBean(ApiBizPageProcessor.class);
            BizResult bizResult = bizProcessor.process(event, request);
            if (bizResult.isSuccess()) {
                composeSuccessResult(bizApiPageResult, bizResult, handler);
            }
            else {
                bizApiPageResult.setErrorResult(ErrorResultUtil.composeErrorResult(bizResult));
            }

        } catch (Exception exception) {
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
            bizApiPageResult.setErrorResult(ErrorResultUtil.composeErrorResult(exception));
        } finally {
            DigestLog digestLog = handler.composeDigestLog(request, bizApiPageResult);
            DigestLogUtil.logDigest(LOGGER, digestLog);
        }

        bizApiPageResult.setTimestamp(DateUtil.getCurrentFormattedDate());
        return bizApiPageResult;
    }

    private static <T> void composeSuccessResult(BizApiPageResult<T> apiPageResult, BizResult bizResult, Handler<T> handler) {
        AssertUtil.notNull(bizResult.getBizPageInfo(), EzErrorCode.SYSTEM_ERROR);

        apiPageResult.setSuccess(true);
        apiPageResult.setPageNumber(bizResult.getBizPageInfo().getPageNumber());
        apiPageResult.setPageSize(bizResult.getBizPageInfo().getPageSize());
        apiPageResult.setNumberRecord(bizResult.getBizPageInfo().getNumberRecord());
        apiPageResult.setTotalRecord(bizResult.getBizPageInfo().getTotalRecord());
        apiPageResult.setTotalPage(bizResult.getBizPageInfo().getTotalPage());
        apiPageResult.setHasNext(bizResult.getBizPageInfo().isHasNext());
        apiPageResult.setBizData(
                bizResult
                        .getBizPageInfo()
                        .getBizData()
                        .stream()
                        .map(handler::convertItem)
                        .collect(Collectors.toList())
        );
    }

    public interface Handler<T> {
        T convertItem(Object object);
        DigestLog composeDigestLog(ApiPageRequest request, BizApiPageResult<T> result);
    }
}