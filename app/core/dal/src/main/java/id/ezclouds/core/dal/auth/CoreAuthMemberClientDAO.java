/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth;

import id.ezclouds.common.facade.dal.auth.AuthMemberClientDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.auth.AuthMemberClient;
import id.ezclouds.core.dal.auth.converter.EzAuthMemberClientQueryConverter;
import id.ezclouds.core.dal.auth.converter.EzAuthMemberClientStoreConverter;
import id.ezclouds.core.dal.auth.dataobject.EzAuthMemberClientDO;
import id.ezclouds.core.dal.auth.repo.EzAuthMemberClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthMemberClientDAO.java, v 0.1 2024‐08‐13 11:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAuthMemberClientDAO implements AuthMemberClientDAO {

    @Autowired
    private EzAuthMemberClientRepository ezAuthMemberClientRepository;

    @EzDAOLogger
    @Override
    public AuthMemberClient getMemberClient(String clientId) {
        EzAuthMemberClientDO memberClientDO = ezAuthMemberClientRepository
                .findById(clientId)
                .orElse(null);
        return new EzAuthMemberClientQueryConverter()
                .convert(memberClientDO);
    }

    @EzDAOLogger
    @Override
    public AuthMemberClient getMemberClient(String orgId, String appId, String loginType, String loginId) {
        EzAuthMemberClientDO memberClientDO = ezAuthMemberClientRepository
                .findByOrgIdAndAppIdAndLoginTypeAndLoginId(orgId, appId, loginType, loginId);
        return new EzAuthMemberClientQueryConverter().convert(memberClientDO);
    }

    @EzDAOLogger
    @Override
    public void store(AuthMemberClient memberClient) {
        EzAuthMemberClientDO memberClientDO = new EzAuthMemberClientStoreConverter()
                .convert(memberClient);
        ezAuthMemberClientRepository.saveAndFlush(memberClientDO);
    }

    @EzDAOLogger
    @Override
    public void updateLoginPassword(String clientId, String password) {
        EzAuthMemberClientDO memberClientDO = ezAuthMemberClientRepository
                .findById(clientId)
                .orElse(null);

        if (memberClientDO != null) {
            memberClientDO.setLoginPassword(password);
            ezAuthMemberClientRepository.saveAndFlush(memberClientDO);
        }
    }
}