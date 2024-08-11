/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member;

import id.ezclouds.common.facade.dal.member.BizMemberBackOfficeDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.constant.SearchScene;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.request.BizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.model.util.PageResultUtil;
import id.ezclouds.core.dal.member.converter.MemberBackOfficeResultConverter;
import id.ezclouds.core.dal.member.dataobject.CoreMemberBackOfficeDO;
import id.ezclouds.core.dal.member.dataobject.CoreMemberExtBackOfficeDO;
import id.ezclouds.core.dal.member.repo.CoreMemberBackOfficeRepository;
import id.ezclouds.core.dal.member.repo.CoreMemberExtBackOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberBackOfficeDAO.java, v 0.1 2024‐08‐11 12:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreMemberBackOfficeDAO implements BizMemberBackOfficeDAO {

    @Autowired
    private CoreMemberBackOfficeRepository coreMemberBackOfficeRepository;

    @Autowired
    private CoreMemberExtBackOfficeRepository coreMemberExtBackOfficeRepository;

    @EzDAOLogger
    @Override
    public PageResult<MemberBackOffice> getMemberPage(BizPageRequest bizPageRequest) {
        SearchScene searchScene = SearchScene.getByCode(bizPageRequest.getSearchScene());

        Page<CoreMemberBackOfficeDO> findResult;
        switch (searchScene) {
            case MEMBER_PHONE:
                findResult = coreMemberBackOfficeRepository
                        .findByOrgIdAndPhone(bizPageRequest.getOrgId(), bizPageRequest.getSearchKeyword(), bizPageRequest.toPageRequest());
                break;

            case MEMBER_NAME_CONTAIN:
                findResult = coreMemberBackOfficeRepository
                        .findByOrgIdAndNameContains(bizPageRequest.getOrgId(), bizPageRequest.getSearchKeyword(), bizPageRequest.toPageRequest());
                break;

            default:
                findResult = coreMemberBackOfficeRepository
                        .findByOrgId(bizPageRequest.getOrgId(), bizPageRequest.toPageRequest());
        }

        List<String> memberIds = findResult
                .getContent()
                .stream()
                .map(CoreMemberBackOfficeDO::getMemberId)
                .collect(Collectors.toList());

        List<CoreMemberExtBackOfficeDO> memberExtensions = coreMemberExtBackOfficeRepository
                .findByMemberIdIn(memberIds);


        return PageResultUtil.convertFindResult(findResult, new MemberBackOfficeResultConverter(memberExtensions));
    }
}