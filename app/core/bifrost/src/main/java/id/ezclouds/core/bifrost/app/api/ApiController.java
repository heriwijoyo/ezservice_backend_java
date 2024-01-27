/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.digestlog.SampleDigestLog;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiController.java, v 0.1 2023‐12‐03 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class ApiController extends AppController {

    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(CommonLoggerConstant.API_CONTROLLER);
    }

    @PostMapping(value = "/api/sample.json", consumes = {MediaType.APPLICATION_JSON_VALUE})
    private ApiResult<String> getSample(@RequestBody ApiRequest request, HttpServletResponse response) {
        return executeInTemplate(ApiEvent.SAMPLE_EVENT, request, new RequestHandler<String>() {
            @Override
            public String convertResult(Object resultObject) {
                return null;
            }
            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<String> result) {
                SampleDigestLog digestLog = new SampleDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }

    @GetMapping(value = "/api/generateKeyIdx.php", consumes = {MediaType.APPLICATION_JSON_VALUE})
    private String getKeyIdx(@RequestBody String request) {
        String allChars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789.";

        List<String> indexes = new ArrayList<>();

        String expectStr = request;
        for (char item : expectStr.toCharArray()) {
            int index = allChars.indexOf(String.valueOf(item));
            indexes.add(String.valueOf(index));
        }

        String response = String.join(",", indexes);
        response += " - " + indexes.size();

        return response;
    }
}