/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.biz.ezservice.model.member.BizMemberRegisterResult;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberRegisterRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberRegisterDigestLog.java, v 0.1 2024‐07‐18 9:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberRegisterDigestLog extends BaseDigestLog<BizMemberRegisterResult> {

    public MemberRegisterDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigest(ApiRequest request, ApiResult<BizMemberRegisterResult> result) {
        String requestData;
        if (request instanceof MemberRegisterRequest) {
            MemberRegisterRequest registerRequest = (MemberRegisterRequest) request;
            requestData = "idCard="+ registerRequest.getIdCardNumber() +",phone="+ registerRequest.getPhone() +",name="+ registerRequest.getName();
        } else {
            requestData = "NULL";
        }

        String requestInfo = "request(" + requestData + ")";
        setDigestMessage(requestInfo);

        setErrorMessage(getErrorMessage(result));
    }
}