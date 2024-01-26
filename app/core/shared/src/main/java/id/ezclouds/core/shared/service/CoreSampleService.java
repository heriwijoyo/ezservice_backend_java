/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.core.shared.model.CoreSample;
import id.ezclouds.core.shared.repo.CoreSampleRepository;
import id.ezclouds.core.shared.repo.dataobject.EzCoreSampleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSampleService.java, v 0.1 2024‐01‐27 3:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreSampleService {

    @Autowired
    private CoreSampleRepository coreSampleRepository;

    public CoreSample getCoreSample() {
        EzCoreSampleDO sampleDO = coreSampleRepository.findById("ABCD").orElse(null);

        if (sampleDO != null) {
            return new CoreSample(sampleDO.getId(), sampleDO.getValue());
        }
        return null;
    }
}