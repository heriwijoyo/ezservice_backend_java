/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.biz.arahindonesia.service.request.BizRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.biz.arahindonesia.service.template.BizServiceTemplate;
import id.ezclouds.core.shared.service.CoreSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRegisterService.java, v 0.1 2023‐12‐31 12:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberRegisterService {

    @Autowired
    private CoreSequenceService coreSequenceService;

    public void registerMember(BizMemberRegisterRequest request) {

        BizServiceTemplate.execute(request, new BizServiceTemplate.Handler<String>() {

            @Override
            public void onRequestCheck(BizRequest request) {
                //TODO: bizRequest validation
            }

            @Override
            public BizResult<String> onBizProcess(BizRequest request) {
                return null;
            }
        });
    }
}