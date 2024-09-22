/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.apibiz;

import id.ezclouds.biz.election.config.BizPublicUrlResolver;
import id.ezclouds.biz.election.config.BizPublicUrlResolverImpl;
import id.ezclouds.biz.election.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.election.model.news.BizNewsDetail;
import id.ezclouds.biz.election.model.news.BizSimpleNews;
import id.ezclouds.biz.election.service.app.NewsInnerService;
import id.ezclouds.biz.election.service.request.BizPageRequest;
import id.ezclouds.common.model.result.BizPageInfo;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizNewsService.java, v 0.1 2023‐12‐11 1:20 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizNewsService extends BizBaseService {

    @Autowired
    private NewsInnerService newsInnerService;

    public BizResult getNews(BizPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                BizPageInfo<BizSimpleNews> bizPageInfo = newsInnerService.getNewsPage(getOrgId(), request);

                BizPublicUrlResolver publicUrlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, getOrgCode(), getOrgId());
                bizPageInfo.getBizData().forEach(simpleNews -> {
                    BizAnnotationProcessor.annotatePublicConfig(simpleNews, publicUrlResolver);
                });

                bizResult.setSuccess(true);
                bizResult.setBizPageInfo(bizPageInfo);
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