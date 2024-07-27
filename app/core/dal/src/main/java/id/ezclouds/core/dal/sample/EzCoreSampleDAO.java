/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.sample;

import id.ezclouds.common.facade.dal.EzSampleDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreSampleDAO.java, v 0.1 2024‐07‐26 3:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreSampleDAO implements EzSampleDAO {

    @EzDAOLogger
    @Override
    public String getSample() {
        return "I am Sample DAO";
    }

    @EzDAOLogger
    @Override
    public long getSampleCount() {
        return 4;
    }

    @EzDAOLogger
    @Override
    public List<String> getSampleList() {
        return Arrays.asList("One", "Two", "Three");
    }

    @EzDAOLogger
    @Override
    public String getException() {
        AssertUtil.isTrue(false, EzErrorCode.DATA_NOT_FOUND);
        return null;
    }
}