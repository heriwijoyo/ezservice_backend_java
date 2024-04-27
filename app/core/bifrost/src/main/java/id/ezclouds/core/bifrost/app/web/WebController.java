/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.biz.ezservice.enums.WebLoadImageScene;
import id.ezclouds.biz.ezservice.model.AppConfig;
import id.ezclouds.biz.ezservice.service.dataservice.AppConfigService;
import id.ezclouds.biz.ezservice.service.dataservice.BizOrganizationService;
import id.ezclouds.biz.ezservice.service.dataservice.model.BizAppBuildPackage;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.bifrost.app.AppController;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.request.WebLoadImageRequest;
import id.ezclouds.core.shared.file.PublicFileResolver;
import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.service.CoreFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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

    @Autowired
    private AppConfigService appConfigService;

    @Value("${ezserviceapp.download.apk_path}")
    private String downloadApkPath;

    @GetMapping(value = "/app/{orgCode}/download/apk/{versionName}")
    public void downloadApk(@PathVariable("orgCode") String orgCode, @PathVariable("versionName") String versionName, HttpServletResponse response) throws IOException {
        CoreOrganization organization = bizOrganizationService.getOrganizationByCode(orgCode);
        if (organization == null) {
            writePageNotFound(response);
            return;
        }

        BizAppBuildPackage buildPackage = appConfigService
                .getBuildPackageByVersionName(organization.getOrgId(), "ANDROID", versionName);
        if (buildPackage == null) {
            writePageNotFound(response);
            return;
        }

        PublicFileResolver publicFileResolver = coreFileService.resolvePublicFileInfo(organization.getOrgId());
        Path apkFile = publicFileResolver.getAppBuildPackagePath(versionName + ".apk");

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

    @GetMapping(value = "/image/private/{scene}/{orgCode}/{memberId}/{fileName}")
    private Void getFamCardImage(
            @PathVariable("scene") String scene,
            @PathVariable("orgCode") String orgCode,
            @PathVariable("memberId") String memberId,
            @PathVariable("fileName") String fileName,
            HttpServletResponse servletResponse) {

        WebLoadImageRequest request = new WebLoadImageRequest();
        request.setScene(WebLoadImageScene.getByCode(scene));
        request.setOrgCode(orgCode);
        request.setMemberId(memberId);
        request.setFileName(fileName);

        return executeWebTemplate(WebEvent.GET_IMAGE_PRIVATE, request, servletResponse, new WebRequestHandler<Void>() {
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

    @GetMapping(value = {"/image/public/{scene}/{orgCode}/{fileName}", "/{scene}/{orgCode}/{fileName}"})
    private Void getPublicImageGallery(
            @PathVariable("scene") String scene,
            @PathVariable("orgCode") String orgCode,
            @PathVariable("fileName") String fileName,
            HttpServletResponse servletResponse) {

        WebLoadImageRequest request = new WebLoadImageRequest();
        request.setScene(WebLoadImageScene.getByCode(scene));
        request.setOrgCode(orgCode);
        request.setFileName(fileName);

        return executeWebTemplate(WebEvent.GET_IMAGE_PUBLIC, request, servletResponse, new WebRequestHandler<Void>() {
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

    @GetMapping(value = "/app/{orgCode}/download.htm")
    private void appDownloadPage(@PathVariable("orgCode") String orgCode, HttpServletResponse servletResponse) {

        CoreOrganization organization = bizOrganizationService.getOrganizationByCode(orgCode);
        if (organization == null) {
            writePageNotFound(servletResponse);
            return;
        }

        BizAppBuildPackage buildPackage = appConfigService
                .getLatestBuildPackage(organization.getOrgId(), "ANDROID");
        if (buildPackage == null) {
            writePageNotFound(servletResponse);
            return;
        }

        AppConfig appConfig = appConfigService.getAppConfig(organization.getOrgId());
        String appName = appConfig.getAppName();

        if (StringUtil.isBlank(appName)) {
            writePageNotFound(servletResponse);
            return;
        }

        try {
            File file = ResourceUtils.getFile("classpath:download.htm");
            String htmlContent = new String(Files.readAllBytes(file.toPath()));
            htmlContent = htmlContent
                    .replace("APP_NAME", appName)
                    .replace("APP_VERSION_NAME", buildPackage.getVersionName());
            servletResponse.getWriter().write(htmlContent);
            servletResponse.getWriter().flush();
        } catch (IOException e) {
            writePageNotFound(servletResponse);
        }
    }

    private void writePageNotFound(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}