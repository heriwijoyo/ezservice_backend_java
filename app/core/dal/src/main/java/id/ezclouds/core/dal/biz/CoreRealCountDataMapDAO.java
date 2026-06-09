/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizRealCountDataMapDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.data.BizRealCountDataMap;
import id.ezclouds.core.dal.biz.converter.BizRealCountDataMapConverter;
import id.ezclouds.core.dal.biz.repo.EzRealCountDataMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreRealCountDataMapDAO.java, v 0.1 2024‐09‐16 11:56 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreRealCountDataMapDAO implements BizRealCountDataMapDAO {

    @Autowired
    private EzRealCountDataMapRepository ezRealCountDataMapRepository;

    @EzDAOLogger
    @Override
    public List<BizRealCountDataMap> getDataMap(String orgId, String scene) {
        BizRealCountDataMapConverter converter = new BizRealCountDataMapConverter();
        return ezRealCountDataMapRepository
                .findByOrgIdAndScene(orgId, scene)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }
}