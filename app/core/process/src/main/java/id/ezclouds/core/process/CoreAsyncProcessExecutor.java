/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process;

import id.ezclouds.common.facade.process.AsyncProcessExecutor;
import id.ezclouds.common.model.process.ProcessName;
import id.ezclouds.common.model.request.process.SurveyResponseParseProcessRequest;
import id.ezclouds.core.process.biz.BizProcessMasterDataAreaInitialize;
import id.ezclouds.core.process.biz.BizProcessReportAreaCommonTable;
import id.ezclouds.core.process.biz.BizProcessorSurveyResponseParse;
import id.ezclouds.core.process.debug.BizProcessDebugger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAsyncProcessExecutor.java, v 0.1 2024‐08‐19 8:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Async
public class CoreAsyncProcessExecutor implements AsyncProcessExecutor {

    @Autowired
    private BizProcessorSurveyResponseParse bizProcessorSurveyResponseParse;

    @Autowired
    private BizProcessReportAreaCommonTable bizProcessReportAreaCommonTable;

    @Autowired
    private BizProcessMasterDataAreaInitialize bizProcessMasterDataAreaInitialize;

    @Autowired
    private BizProcessDebugger bizProcessDebugger;

    @Override
    public void execute(ProcessName processName, String param) {
        switch (processName) {
            case SURVEY_RESPONSE_PARSE:
                String[] params = param.split(",");
                SurveyResponseParseProcessRequest request = new SurveyResponseParseProcessRequest();
                request.setOrgId(params[0]);
                request.setSurveyId(params[1]);
                bizProcessorSurveyResponseParse.process(request);
                break;
            case REPORT_AREA_COMMON_TABLE_PARSE:
                bizProcessReportAreaCommonTable.process(param);
                break;
            case MASTER_DATA_AREA_INIT:
                bizProcessMasterDataAreaInitialize.process(param);
                break;

            case DEBUG:
                bizProcessDebugger.process(null);
                break;
            default:
                break;
        }
    }
}