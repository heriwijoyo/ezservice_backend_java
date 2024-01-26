/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.core.shared.model.CoreSample;
import id.ezclouds.core.shared.service.CoreSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSampleService.java, v 0.1 2024‐01‐27 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizSampleService {

    @Autowired
    private CoreSampleService coreSampleService;

    public CoreSample getCoreSample() {
        return coreSampleService.getCoreSample();
    }
}