/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.biz.election;

import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.request.api.ApiEvent;
import id.ezclouds.common.model.request.api.ApiRequest;
import id.ezclouds.common.model.request.api.election.VoterRegisterRequest;
import id.ezclouds.common.model.result.api.ApiResult;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.BizApiControllerTemplate;
import id.ezclouds.core.bifrost.app.api.digestlog.VoterRegisterDigestLog;
import id.ezclouds.core.bifrost.app.api.handler.BizApiTemplateHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiBizVoterController.java, v 0.1 2024‐09‐29 5:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class ApiBizVoterController {

    @PostMapping("/api/v2/election/voterRegister.json")
    private ApiResult<BizVoter> registerVoter(@RequestBody VoterRegisterRequest request) {
        return BizApiControllerTemplate.execute(ApiEvent.API_VOTER_REGISTER, request, new BizApiTemplateHandler<>() {
            @Override
            public DigestLog composeDigestLog(ApiRequest request, ApiResult<BizVoter> result) {
                VoterRegisterDigestLog digestLog = new VoterRegisterDigestLog(result.isSuccess(), result.getResultCode());
                digestLog.composeDigest(request, result);
                return digestLog;
            }
        });
    }
}