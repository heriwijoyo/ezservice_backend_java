/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.sample;

import id.ezclouds.common.facade.dal.SampleDalService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SampleOneDalService.java, v 0.1 2024‐07‐24 10:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Qualifier("sampleOneDalService")
public class SampleOneDalService implements SampleDalService {

    @Override
    public String fetchRandomChar(String key) {
        return "ONE";
    }
}