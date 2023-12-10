/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.data;

import id.ezclouds.common.dal.model.AppProfileDO;
import id.ezclouds.common.dal.profile.AppProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppProfileService.java, v 0.1 2023‐12‐11 12:52 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppProfileService {

    @Autowired
    private AppProfileRepository appProfileRepository;

    @Cacheable("app_profile")
    public List<AppProfileDO> getAllAppProfile() {
        return appProfileRepository.findAll();
    }
}