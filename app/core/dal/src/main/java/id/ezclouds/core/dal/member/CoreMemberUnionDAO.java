/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member;

import id.ezclouds.common.facade.dal.member.BizMemberUnionDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.core.dal.member.repo.CoreMemberUnionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberUnionDAO.java, v 0.1 2024‐07‐28 6:17 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreMemberUnionDAO implements BizMemberUnionDAO {

    @Autowired
    private CoreMemberUnionRepository coreMemberUnionRepository;

    @EzDAOLogger
    @Override
    public long countByOrgId(String orgId) {
        return coreMemberUnionRepository.countByOrgId(orgId);
    }
}