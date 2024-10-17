/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth;

import id.ezclouds.common.facade.dal.auth.AuthAppClientDAO;
import id.ezclouds.common.model.auth.AuthAppClient;
import id.ezclouds.core.dal.auth.converter.EzAuthAppClientQueryConverter;
import id.ezclouds.core.dal.auth.dataobject.DalAuthAppClientDO;
import id.ezclouds.core.dal.auth.repo.DalAuthAppClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthAppClientDAO.java, v 0.1 2024‐08‐13 5:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAuthAppClientDAO implements AuthAppClientDAO {

    @Autowired
    private DalAuthAppClientRepository dalAuthAppClientRepository;

    @Override
    public List<AuthAppClient> getAllActive() {
        EzAuthAppClientQueryConverter converter = new EzAuthAppClientQueryConverter();
        return dalAuthAppClientRepository
                .findByStatus(1)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public AuthAppClient getByOrgId(String orgId) {
        DalAuthAppClientDO result = dalAuthAppClientRepository
                .findByOrgId(orgId);

        return new EzAuthAppClientQueryConverter()
                .convert(result);
    }
}