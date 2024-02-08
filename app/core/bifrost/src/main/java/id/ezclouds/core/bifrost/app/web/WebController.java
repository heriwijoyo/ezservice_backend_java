/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.biz.ezservice.service.dataservice.BizOrganizationService;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.bifrost.app.AppController;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.request.WebLoadImageRequest;
import id.ezclouds.core.shared.service.CoreFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebController.java, v 0.1 2024‐01‐28 3:40 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class WebController extends AppController {

    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(CommonLoggerConstant.WEB_CONTROLLER);
    }

    @Autowired
    private BizOrganizationService bizOrganizationService;

    @Autowired
    private CoreFileService coreFileService;

    @Value("${ezserviceapp.download.apk_path}")
    private String downloadApkPath;

    @RequestMapping(value = "/")
    private String getIndexPage() {
        return "Welcome to Arah Indonesia";
    }

    @GetMapping(value = "/rjlapp/download/apk")
    public void downloadApk(@RequestParam String file, HttpServletResponse response) throws IOException {
        Path apkFile = Paths.get(downloadApkPath, file);

        response.setContentType("application/vnd.android.package-archive");
        response.setContentLengthLong(Files.size(apkFile));
        response.setHeader(
                HttpHeaders.CONTENT_DISPOSITION,
                ContentDisposition.attachment()
                .filename(apkFile.getFileName().toString(), StandardCharsets.UTF_8)
                .build()
                .toString()
        );

        Files.copy(apkFile, response.getOutputStream());
    }

    @GetMapping(value = "/image/avatar/{orgCode}/{memberId}/{fileName}")
    private Void getAvatarImage(
            @PathVariable("orgCode") String orgCode,
            @PathVariable("memberId") String memberId,
            @PathVariable("fileName") String fileName,
            HttpServletResponse servletResponse) {

        WebLoadImageRequest request = new WebLoadImageRequest();
        request.setScene("AVATAR");
        request.setOrgCode(orgCode);
        request.setMemberId(memberId);
        request.setFileName(fileName);

        return executeWebTemplate(WebEvent.GET_IMAGE_AVATAR, request, servletResponse, new WebRequestHandler<Void>() {
            @Override
            public Void convertResult(Object resultObject) {
                return null;
            }

            @Override
            public void onException(Exception exception) {
                servletResponse.setStatus(HttpStatus.NOT_FOUND.value());
            }

            @Override
            public String composeDigestLog() {
                return "request(scene=" +
                        request.getScene() +
                        ",orgCode=" +
                        request.getOrgCode() +
                        ",memberId=" +
                        request.getMemberId() +
                        ",fileName=" +
                        request.getFileName() +
                        ")";
            }
        });
    }
}