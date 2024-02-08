/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.innerprocess;

import id.ezclouds.biz.ezservice.service.request.BizImageLoadRequest;
import id.ezclouds.biz.ezservice.service.webbiz.BizMemberWebService;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.bifrost.app.web.request.WebLoadImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizInnerProcessor.java, v 0.1 2024‐02‐09 12:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class WebBizInnerProcessor {

    @Autowired
    private BizMemberWebService bizMemberWebService;

    public void loadCommonImage(Object request, HttpServletResponse response) throws Exception {

        WebBizInnerProcessTemplate.execute(request, new WebBizInnerProcessTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.MEDIA_NOT_FOUND);
                AssertUtil.isTrue(request instanceof WebLoadImageRequest, EzErrorCode.MEDIA_NOT_FOUND);

                WebLoadImageRequest loadImageRequest = (WebLoadImageRequest) request;
                AssertUtil.notBlank(loadImageRequest.getScene(), EzErrorCode.MEDIA_NOT_FOUND);
                AssertUtil.notBlank(loadImageRequest.getOrgCode(), EzErrorCode.MEDIA_NOT_FOUND);
                AssertUtil.notBlank(loadImageRequest.getFileName(), EzErrorCode.MEDIA_NOT_FOUND);
            }

            @Override
            public void onProcess() throws Exception {
                BizImageLoadRequest bizImageLoadRequest = new BizImageLoadRequest();

                WebLoadImageRequest loadImageRequest = (WebLoadImageRequest) request;
                bizImageLoadRequest.setScene(loadImageRequest.getScene());
                bizImageLoadRequest.setOrgCode(loadImageRequest.getOrgCode());
                bizImageLoadRequest.setMemberId(loadImageRequest.getMemberId());
                bizImageLoadRequest.setFileName(loadImageRequest.getFileName());
                Path imagePath = bizMemberWebService.bizLoadCommonImage(bizImageLoadRequest);

                Files.copy(imagePath, response.getOutputStream());
            }

            @Override
            public void onEzException(EzErrorException ezException) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
        });

    }
}