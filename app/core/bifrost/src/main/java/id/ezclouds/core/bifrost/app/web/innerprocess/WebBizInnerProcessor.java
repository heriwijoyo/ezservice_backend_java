/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.innerprocess;

import id.ezclouds.biz.election.service.request.BizImageLoadRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.biz.election.service.webbiz.BizMemberWebService;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.bifrost.app.web.request.WebLoadImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        AssertUtil.isTrue((request instanceof WebLoadImageRequest), EzErrorCode.MEDIA_NOT_FOUND);
        WebLoadImageRequest loadImageRequest = (WebLoadImageRequest) request;

        BizImageLoadRequest bizImageLoadRequest = new BizImageLoadRequest();
        bizImageLoadRequest.setScene(loadImageRequest.getScene());
        bizImageLoadRequest.setOrgCode(loadImageRequest.getOrgCode());
        bizImageLoadRequest.setMemberId(loadImageRequest.getMemberId());
        bizImageLoadRequest.setFileName(loadImageRequest.getFileName());

        BizResult bizResult = bizMemberWebService.bizLoadCommonImage(bizImageLoadRequest);
        if (bizResult.isSuccess()) {
            Path imagePath = (Path) bizResult.getObject();

            String fileExt = StringUtils.getFilenameExtension(imagePath.toString());
            if (fileExt != null && fileExt.equalsIgnoreCase("pdf")) {
                String filename = loadImageRequest.getFileName();
                response.setContentType("application/pdf");
                response.setHeader("content-disposition", "inline; filename=\""+ filename +"\"");
            }
            response.setContentLengthLong(Files.size(imagePath));
            Files.copy(imagePath, response.getOutputStream());
        } else {
            throw new EzErrorException(bizResult.getErrorCode(), bizResult.getErrorCode().getDescription());
        }
    }
}