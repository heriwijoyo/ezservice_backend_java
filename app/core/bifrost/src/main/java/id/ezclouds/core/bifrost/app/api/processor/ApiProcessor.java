/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.processor;

import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiProcessor.java, v 0.1 2024‐09‐29 12:43 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class ApiProcessor {

    public BizResult process(ApiEvent apiEvent, ApiRequest request) {
        switch (apiEvent) {
            case API_VOTER_REGISTER:
                return null;
        }

        return new BizResult();
    }
}