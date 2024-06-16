/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.biz.ezservice.enums.WebLoadImageScene;
import id.ezclouds.biz.ezservice.model.AppConfig;
import id.ezclouds.biz.ezservice.service.app.AppBuildPackageService;
import id.ezclouds.biz.ezservice.service.app.AppConfigService;
import id.ezclouds.biz.ezservice.service.app.BizOrganizationService;
import id.ezclouds.biz.ezservice.service.app.model.AppBuildType;
import id.ezclouds.biz.ezservice.service.app.model.BizAppBuildPackage;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.bifrost.app.AppController;
import id.ezclouds.core.bifrost.app.api.digestlog.CommonWebDigestLog;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.request.WebLoadImageRequest;
import id.ezclouds.core.bifrost.core.util.ErrorResultUtil;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.file.PublicFileResolver;
import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.service.CoreFileService;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private AppBuildPackageService appBuildPackageService;

    @Value("${ezserviceapp.download.apk_path}")
    private String downloadApkPath;

    @GetMapping(value = "/")
    private void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
    }

    @GetMapping(value = "/app/{orgCode}/download/apk/{appName}-{versionName}.apk")
    public void downloadApk(
            @PathVariable("orgCode") String orgCode,
            @PathVariable("appName") String appName,
            @PathVariable("versionName") String versionName,
            HttpServletResponse response) {

        EzAppContextHolder.init(WebEvent.WEB_DOWNLOAD_APK);
        ErrorResult errorResult = null;

        try {
            CoreOrganization organization = bizOrganizationService.getOrganizationByCode(orgCode);
            AssertUtil.notNull(organization, EzErrorCode.DATA_NOT_FOUND, "org not found");

            AppConfig appConfig = appConfigService.getAppConfig(organization.getOrgId());
            AssertUtil.notNull(appConfig, EzErrorCode.DATA_NOT_FOUND, "appConfig not found");
            AssertUtil.isTrue(StringUtil.equalsNotNull(appName, appConfig.getAppName()), EzErrorCode.DATA_NOT_FOUND, "invalid appName");

            BizAppBuildPackage buildPackage = appBuildPackageService
                    .getBuildPackageByVersionName(organization.getOrgId(), "ANDROID", versionName);
            AssertUtil.notNull(buildPackage, EzErrorCode.DATA_NOT_FOUND, "buildPackage not found");

            PublicFileResolver publicFileResolver = coreFileService.resolvePublicFileInfo(organization.getOrgId());
            Path apkFile = publicFileResolver.getAppBuildPackagePath(versionName + ".apk");
            String apkFileName = appName +"-"+ versionName + ".apk";

            response.setContentType("application/vnd.android.package-archive");
            response.setContentLengthLong(Files.size(apkFile));
            response.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION,
                    ContentDisposition.attachment()
                            .filename(apkFileName, StandardCharsets.UTF_8)
                            .build()
                            .toString()
            );

            Files.copy(apkFile, response.getOutputStream());
        }
        catch (Exception exception) {
            EzAppContextHolder
                    .getContext()
                    .appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
            errorResult = ErrorResultUtil.composeErrorResult(exception);
        }
        finally {
            boolean success = errorResult == null;
            String resultCode = errorResult == null ? "RESULT_SUCCESS" : errorResult.getErrorCode();
            CommonWebDigestLog webDigestLog = new CommonWebDigestLog(success, resultCode);
            webDigestLog.setDigestMessage("request(orgCode="+ orgCode +")");
            webDigestLog.setErrorMessage(errorResult);
            DigestLogUtil.logWebDigest(getLogger(), webDigestLog);

            if (!success) {
                writePageNotFound(response);
            }
        }

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
    private void appDownloadPage(@PathVariable(name = "orgCode", required = false) String orgCode, HttpServletResponse response) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ORG_DOWNLOAD);
        ErrorResult errorResult = null;

        try {
            CoreOrganization organization = bizOrganizationService.getOrganizationByCode(orgCode);
            AssertUtil.notNull(organization, EzErrorCode.DATA_NOT_FOUND, "organization not found");

            BizAppBuildPackage buildPackage = appBuildPackageService
                    .getLatestBuildPackage(organization.getOrgId(), AppBuildType.ANDROID.getCode());
            AssertUtil.notNull(buildPackage, EzErrorCode.DATA_NOT_FOUND, "buildPackage not found");

            AppConfig appConfig = appConfigService.getAppConfig(organization.getOrgId());
            AssertUtil.notNull(appConfig, EzErrorCode.DATA_NOT_FOUND, "appConfig not found");
            AssertUtil.notBlank(appConfig.getAppName(), EzErrorCode.DATA_NOT_FOUND, "appConfig.name is blank");

            Resource downloadResource = new ClassPathResource("download.htm");

            String htmlContent = StreamUtils.copyToString(downloadResource.getInputStream(), StandardCharsets.UTF_8);
            htmlContent = htmlContent
                    .replace("APP_NAME", appConfig.getAppName())
                    .replace("ORG_CODE", orgCode)
                    .replace("APP_VERSION_NAME", buildPackage.getVersionName());
            response.getWriter().write(htmlContent);
            response.getWriter().flush();
        }
        catch (Exception exception) {
            EzAppContextHolder
                    .getContext()
                    .appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
            errorResult = ErrorResultUtil.composeErrorResult(exception);
        }
        finally {
            boolean success = errorResult == null;
            String resultCode = errorResult == null ? "RESULT_SUCCESS" : errorResult.getErrorCode();
            CommonWebDigestLog webDigestLog = new CommonWebDigestLog(success, resultCode);
            webDigestLog.setDigestMessage("request(orgCode="+ orgCode +")");
            webDigestLog.setErrorMessage(errorResult);
            DigestLogUtil.logWebDigest(getLogger(), webDigestLog);

            if (!success) {
                writePageNotFound(response);
            }
        }
    }

    private void writePageNotFound(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}