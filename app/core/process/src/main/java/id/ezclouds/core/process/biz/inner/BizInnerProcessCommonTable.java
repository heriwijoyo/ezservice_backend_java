/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessCommonTable.java, v 0.1 2024‐08‐26 9:23 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizInnerProcessCommonTable {

    private static final String COLUMNS = "[{readOnly:true,align:'left',width:200,title:'Nama Pekon'},{readOnly:true,align:'left',width:50,title:'Total'},{readOnly:true,align:'left',width:50,title:'Strong'},{readOnly:true,align:'left',width:50,title:'Lazy'},{readOnly:true,align:'left',width:50,title:'L'},{readOnly:true,align:'left',width:50,title:'P'},{readOnly:true,align:'left',width:50,title:'TPS 01'},{readOnly:true,align:'left',width:50,title:'TPS 02'},{readOnly:true,align:'left',width:50,title:'TPS 03'},{readOnly:true,align:'left',width:50,title:'TPS 04'},{readOnly:true,align:'left',width:50,title:'TPS 05'},{readOnly:true,align:'left',width:50,title:'TPS 06'}]";

    @Autowired
    private BizCommonTableDAO bizCommonTableDAO;

    @Transactional
    public BizCommonTable getOrCreateCommonTable(String orgId, String code) {
        BizCommonTable commonTable = bizCommonTableDAO
                .getByCode(orgId, code);
        if (commonTable == null) {
            commonTable = new BizCommonTable();
            commonTable.setTableId(HashUtil.createHash(orgId, code));
            commonTable.setOrgId(orgId);
            commonTable.setCode(code);
            commonTable.setTitle("Template Title");
            commonTable.setColumns("{}");
            commonTable.setCreatedTime(DateUtil.getCurrentFormattedDate());
            commonTable.setStatus(1);
            bizCommonTableDAO.store(commonTable);
        }
        return commonTable;
    }

    @Transactional
    public void updateCommonTable(BizCommonTable commonTable) {
        commonTable.setColumns(COLUMNS);
        bizCommonTableDAO.store(commonTable);
    }
}