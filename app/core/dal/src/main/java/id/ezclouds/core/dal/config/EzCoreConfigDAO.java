/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.config;

import id.ezclouds.common.facade.dal.config.CoreConfigDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.dal.config.converter.EzCoreOrgConfigConverter;
import id.ezclouds.core.dal.config.repo.EzCoreOrgConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreConfigDAO.java, v 0.1 2024‐08‐29 11:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreConfigDAO implements CoreConfigDAO {

    @Autowired
    private EzCoreOrgConfigRepository ezCoreOrgConfigRepository;

    @EzDAOLogger
    @Override
    public CoreConfig getConfig(String orgId, String configKey) {
        return new EzCoreOrgConfigConverter().convertQuery(
                ezCoreOrgConfigRepository.findByOrgIdAndConfigKey(orgId, configKey)
        );
    }

    @EzDAOLogger
    @Override
    public void storeConfig(CoreConfig coreConfig) {
        if (StringUtil.isNotBlank(coreConfig.getOrgId())) {
            ezCoreOrgConfigRepository
                    .saveAndFlush(new EzCoreOrgConfigConverter().convertStore(coreConfig));
        }
    }
}