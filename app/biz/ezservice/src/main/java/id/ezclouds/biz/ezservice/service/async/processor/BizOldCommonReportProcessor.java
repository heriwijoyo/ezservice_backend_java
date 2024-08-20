/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.processor;

import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportRepository;
import id.ezclouds.biz.ezservice.service.request.BizAsyncProcessRequest;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizOldCommonReportProcessor.java, v 0.1 2024‐07‐06 2:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizOldCommonReportProcessor implements BizOldAsyncProcessor {

    @Autowired
    private BizMemberImportRepository bizMemberImportRepository;

    @Autowired
    private AppSubOrganizationRepository appSubOrganizationRepository;

    @Override
    public void process(BizAsyncProcessRequest request) {
        //
    }
}