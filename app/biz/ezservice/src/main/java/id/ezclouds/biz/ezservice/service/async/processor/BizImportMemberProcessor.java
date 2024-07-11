/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import id.ezclouds.biz.ezservice.enums.BizAsyncScene;
import id.ezclouds.biz.ezservice.service.async.parser.ImportMemberParser;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportFailedDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportFailedRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportRepository;
import id.ezclouds.biz.ezservice.service.request.BizAsyncProcessRequest;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizImportMemberProcessor.java, v 0.1 2024‐07‐07 6:35 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizImportMemberProcessor implements BizAsyncProcessor {

    @Autowired
    private BizMemberImportRepository bizMemberImportRepository;

    @Autowired
    private BizMemberImportFailedRepository bizMemberImportFailedRepository;

    @Override
    public void process(BizAsyncProcessRequest request) {
        if (request == null
                || request.getBizAsyncScene() == null
                || request.getBizAsyncScene() != BizAsyncScene.SYNC_BULK_MEMBER_DATA_IMPORT) {
            return;
        }

        String subOrgId = (String) request.getPayload().get("SUB_ORG_ID");
        String fileId = (String) request.getPayload().get("FILE_ID");

        List<BizMemberImportDO> currentData = bizMemberImportRepository
                .findByOrgIdAndSourceId(request.getOrgId(), fileId);
        if (currentData.size() > 0) {
            for (BizMemberImportDO bizMemberImportDO : currentData) {
                bizMemberImportRepository.delete(bizMemberImportDO);
            }
            bizMemberImportRepository.flush();
        }

        List<BizMemberImportFailedDO> currentDataFailed = bizMemberImportFailedRepository
                .findByOrgIdAndSourceId(request.getOrgId(), fileId);
        if (currentDataFailed.size() > 0) {
            for (BizMemberImportFailedDO failedDO : currentDataFailed) {
                bizMemberImportFailedRepository.delete(failedDO);
            }
            bizMemberImportFailedRepository.flush();
        }

        ImportMemberParser importMemberParser = new ImportMemberParser(request.getOrgId(), subOrgId, fileId);
        List<String[]> lines = new ArrayList<>();
        Path filePath = (Path) request.getPayload().get("FILE_PATH");
        try {
            Reader reader = Files.newBufferedReader(filePath);
            CSVReader csvReader = new CSVReader(reader);
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {}


        for (String[] line : lines) {
            BizMemberImportDO memberImportDO = importMemberParser.parseLine(line);
            try {
                String name = memberImportDO.getName();
                if (StringUtil.isNotBlank(name) && !"NAMA".equals(name.toUpperCase())) {
                    mapValues(memberImportDO);
                    bizMemberImportRepository.saveAndFlush(memberImportDO);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("FAILED TO IMPORT");
                tryStoreFailedPayload(memberImportDO);
            }
        }
    }

    private void mapValues(BizMemberImportDO memberImportDO) {
        String gender = memberImportDO.getGender();
        if ("L".equals(gender)) {
            memberImportDO.setGender("MALE");
        }
        if ("P".equals(gender)) {
            memberImportDO.setGender("FEMALE");
        }
    }

    private void tryStoreFailedPayload(BizMemberImportDO memberImportDO) {
        try {
            BizMemberImportFailedDO importFailedDO = new BizMemberImportFailedDO();
            importFailedDO.setId(memberImportDO.getBizMemberId());
            importFailedDO.setOrgId(memberImportDO.getOrgId());
            importFailedDO.setSourceId(memberImportDO.getSourceId());
            importFailedDO.setPayload(new ObjectMapper().writeValueAsString(memberImportDO));
            bizMemberImportFailedRepository.saveAndFlush(importFailedDO);
        } catch (Exception e) {}
    }
}