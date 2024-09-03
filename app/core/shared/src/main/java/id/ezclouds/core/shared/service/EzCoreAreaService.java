/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.area.CoreAreaService;
import id.ezclouds.common.model.area.CoreArea;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreAreaService.java, v 0.1 2024‐09‐03 11:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreAreaService implements CoreAreaService {

    @Override
    public List<CoreArea> getByParentId(String parentId) {
        return null;
    }
}