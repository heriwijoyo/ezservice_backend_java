/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process;

import id.ezclouds.common.facade.process.MemberImportProcessor;
import id.ezclouds.common.model.request.FileStreamImportRequest;
import id.ezclouds.common.model.result.BaseResult;
import id.ezclouds.core.process.biz.BizMemberImportProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import id.ezclouds.core.process.template.CoreProcessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberImportProcessor.java, v 0.1 2024‐08‐11 5:09 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberImportProcessor implements MemberImportProcessor {

    @Autowired
    private BizMemberImportProcessor bizMemberImportProcessor;

    @Override
    public BaseResult process(FileStreamImportRequest request) {
        final BaseResult baseResult = new BaseResult();
        final List<String> logData = new ArrayList<>();

        CoreProcessTemplate.execute(BizProcessEvent.MEMBER_IMPORT_CSV, new CoreProcessTemplate.Handler() {
            @Override
            public void doStart(BizProcessEvent processEvent) {

            }

            @Override
            public boolean doProcess(BizProcessEvent processEvent) {
                //TODO: add request validation later
                String orgId = request.getOrgId();
                String subOrgId = request.getSubOrgId();

                bizMemberImportProcessor.deleteAllImport(orgId, subOrgId);
                bizMemberImportProcessor.deleteAllImportFailed(orgId, subOrgId);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
                bufferedReader
                        .lines()
                        .forEach(line -> {
                            System.out.println(line);
                        });

                baseResult.setSuccess(true);
                baseResult.setObject("PROCESS SUCCESS");
                return true;
            }

            @Override
            public void doFinish(BizProcessEvent processEvent) {

            }

            @Override
            public List<String> getLogData() {
                return logData;
            }
        });

        return baseResult;
    }
}