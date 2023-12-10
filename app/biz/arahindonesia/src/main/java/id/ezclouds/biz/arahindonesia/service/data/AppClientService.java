/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.data;

import id.ezclouds.biz.arahindonesia.converter.ModelConverter;
import id.ezclouds.biz.arahindonesia.model.AppClient;
import id.ezclouds.common.dal.AppClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppClientService.java, v 0.1 2023‐12‐07 2:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppClientService {

    @Autowired
    private AppClientRepository appClientRepository;

    @Cacheable("appClients")
    public List<AppClient> getAppClients() {
        return appClientRepository
                .findAll()
                .stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }
}