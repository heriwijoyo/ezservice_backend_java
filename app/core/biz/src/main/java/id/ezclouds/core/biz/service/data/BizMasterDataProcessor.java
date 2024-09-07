/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.dal.biz.BizMasterDataDAO;
import id.ezclouds.common.model.biz.data.SmartDataEvent;
import id.ezclouds.common.model.biz.data.SmartDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataProcessor.java, v 0.1 2024‐09‐06 11:43 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMasterDataProcessor implements SmartDataProcessor {

    @Autowired
    private BizMasterDataDAO bizMasterDataDAO;

    @Override
    public SmartDataSource syncData(SmartDataSource data) {
        if (data.getEvent() == SmartDataEvent.DATA_QUERY) {

        }
        return null;
    }


}