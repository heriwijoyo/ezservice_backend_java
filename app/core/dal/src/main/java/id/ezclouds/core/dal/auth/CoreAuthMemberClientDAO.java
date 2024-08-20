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
import id.ezclouds.core.dal.auth.dataobject.DalAuthMemberClientDO;
import id.ezclouds.core.dal.auth.repo.DalAuthMemberClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthMemberClientDAO.java, v 0.1 2024‐08‐13 11:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAuthMemberClientDAO implements AuthMemberClientDAO {

    @Autowired
    private DalAuthMemberClientRepository dalAuthMemberClientRepository;

    @EzDAOLogger
    @Override
    public AuthMemberClient getMemberClient(String clientId) {
        DalAuthMemberClientDO memberClientDO = dalAuthMemberClientRepository
                .findById(clientId)
                .orElse(null);
        return new EzAuthMemberClientQueryConverter()
                .convert(memberClientDO);
    }

    @EzDAOLogger
    @Override
    public AuthMemberClient getMemberClient(String orgId, String appId, String loginType, String loginId) {
        DalAuthMemberClientDO memberClientDO = dalAuthMemberClientRepository
                .findByOrgIdAndAppIdAndLoginTypeAndLoginId(orgId, appId, loginType, loginId);
        return new EzAuthMemberClientQueryConverter().convert(memberClientDO);
    }

    @EzDAOLogger
    @Override
    public void store(AuthMemberClient memberClient) {
        DalAuthMemberClientDO memberClientDO = new EzAuthMemberClientStoreConverter()
                .convert(memberClient);
        dalAuthMemberClientRepository.saveAndFlush(memberClientDO);
    }

    @EzDAOLogger
    @Override
    public void updateLoginPassword(String clientId, String password) {
        DalAuthMemberClientDO memberClientDO = dalAuthMemberClientRepository
                .findById(clientId)
                .orElse(null);

        if (memberClientDO != null) {
            memberClientDO.setLoginPassword(password);
            dalAuthMemberClientRepository.saveAndFlush(memberClientDO);
        }
    }
}