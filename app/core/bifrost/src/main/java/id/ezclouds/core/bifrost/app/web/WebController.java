/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/rjlapp")
public class WebController {

    @Value("${ezserviceapp.download.apk_path}")
    private String downloadApkPath;

    @GetMapping(value = "/download/apk")
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
}