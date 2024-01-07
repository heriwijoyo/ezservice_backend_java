/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.service.core.MemberService;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.biz.arahindonesia.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.service.CoreUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberService.java, v 0.1 2023‐12‐31 12:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberService {

    private static final String DEFAULT_LOGIN_TYPE = "PHONE";

    @Autowired
    private MemberService memberService;

    public BizResult registerMember(BizMemberRegisterRequest request) throws EzErrorException {
        final BizResult bizResult = new BizResult();
        final String orgId = EzAppContextHolder.getContext().getOrgId();

        BizServiceTemplate.execute(bizResult, new BizServiceTemplate.Handler() {

            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "request (BizMemberRegisterRequest) is null");
                AssertUtil.notBlank(request.getSourceId(), EzErrorCode.ILLEGAL_PARAM, "request.sourceId is blank");
                AssertUtil.notBlank(request.getRoles(), EzErrorCode.ILLEGAL_PARAM, "request.roles is blank");
                AssertUtil.notBlank(request.getName(), EzErrorCode.ILLEGAL_PARAM, "request.name is blank");
                AssertUtil.notNull(request.getBizGender(), EzErrorCode.ILLEGAL_PARAM, "request.bizGender is null");
                AssertUtil.notBlank(request.getDateOfBirth(), EzErrorCode.ILLEGAL_PARAM, "request.dateOfBirth is blank");
                AssertUtil.notBlank(request.getPhone(), EzErrorCode.ILLEGAL_PARAM, "request.phone is blank");
                AssertUtil.notBlank(request.getAddress(), EzErrorCode.ILLEGAL_PARAM, "request.address is blank");
            }

            @Override
            public void onBizProcess() throws EzErrorException {
                bizResult.setObject(memberService.processRegisterMember(request));
                bizResult.setSuccess(true);
            }
        });

        return bizResult;
    }
}