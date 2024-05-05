/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.config.BizPublicUrlResolverImpl;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.model.news.BizNewsDetail;
import id.ezclouds.biz.ezservice.model.news.BizSimpleNews;
import id.ezclouds.biz.ezservice.service.dataservice.NewsInnerService;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizNewsService.java, v 0.1 2023‐12‐11 1:20 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizNewsService extends BizBaseService {

    private static final int PAGE_NUMBER_DEFAULT = 1;
    private static final int PAGE_SIZE_DEFAULT = 10;

    @Autowired
    private NewsInnerService newsInnerService;

    public BizResult getNews(BizPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {

            }

            @Override
            public void onBizProcess() throws Exception {

            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult getActiveNews() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws EzErrorException {
                ListResult<BizSimpleNews> result = new ListResult<>();
                result.setPageNumber(1);
                result.setHasMore(false);
                result.setItems(newsInnerService.getActiveListNews());

                bizResult.setSuccess(true);
                bizResult.setObject(result);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult getNewsDetail(String newsId) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(newsId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                BizNewsDetail detail = newsInnerService.getNewsDetail(newsId);

                BizPublicUrlResolver publicUrlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, getOrgCode(), getOrgId());
                BizAnnotationProcessor.annotatePublicConfig(detail, publicUrlResolver);

                bizResult.setObject(detail);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}