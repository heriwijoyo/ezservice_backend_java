/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.model.news.SimpleNews;
import id.ezclouds.biz.arahindonesia.service.dataservice.NewsService;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.biz.arahindonesia.service.template.BizServiceTemplate;
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

    @Autowired
    private NewsService newsService;

    public BizResult getActiveNews() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws EzErrorException {
                ListResult<SimpleNews> result = new ListResult<>();
                result.setPageNumber(1);
                result.setHasMore(false);
                result.setItems(newsService.getActiveListNews());

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
}