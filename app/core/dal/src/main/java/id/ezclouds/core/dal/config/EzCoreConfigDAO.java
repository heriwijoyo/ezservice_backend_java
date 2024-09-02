/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.config;

import id.ezclouds.common.facade.dal.config.CoreConfigDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.model.config.CoreConfigType;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.dal.config.converter.EzCoreOrgConfigConverter;
import id.ezclouds.core.dal.config.repo.EzCoreOrgConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreConfigDAO.java, v 0.1 2024‐08‐29 11:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreConfigDAO implements CoreConfigDAO {

    @Autowired
    private EzCoreOrgConfigRepository ezCoreOrgConfigRepository;

    @Override
    public List<CoreConfig> getAllConfig(String orgId) {
        EzCoreOrgConfigConverter converter = new EzCoreOrgConfigConverter();
        return ezCoreOrgConfigRepository
                .findByOrgId(orgId)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }

    @EzDAOLogger
    @Override
    public CoreConfig getConfig(String orgId, CoreConfigType configType) {
        return new EzCoreOrgConfigConverter().convertQuery(
                ezCoreOrgConfigRepository.findByOrgIdAndConfigKey(orgId, configType.getCode())
        );
    }

    @EzDAOLogger
    @Override
    public CoreConfig getConfig(CoreConfigType configType) {
        return null;
    }

    @EzDAOLogger
    @Override
    public void store(CoreConfig coreConfig) {
        if (StringUtil.isNotBlank(coreConfig.getOrgId())) {
            ezCoreOrgConfigRepository
                    .saveAndFlush(new EzCoreOrgConfigConverter().convertStore(coreConfig));
        }
    }
}