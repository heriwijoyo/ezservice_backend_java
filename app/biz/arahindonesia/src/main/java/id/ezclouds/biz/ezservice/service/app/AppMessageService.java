/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.model.app.AppMessage;
import id.ezclouds.biz.ezservice.service.app.converter.AppModelConverter;
import id.ezclouds.biz.ezservice.service.app.dataobject.AppMessageMemberDO;
import id.ezclouds.biz.ezservice.service.app.repo.AppMessageMemberRepository;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.biz.ezservice.service.result.BizPageInfo;
import id.ezclouds.biz.ezservice.util.PageRequestUtil;
import id.ezclouds.biz.ezservice.util.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMessageService.java, v 0.1 2024‐05‐09 12:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppMessageService {

    @Autowired
    private AppMessageMemberRepository appMessageMemberRepository;

    public BizPageInfo<AppMessage> getAppMessage(String orgId, String memberId, BizPageRequest request) {
        request.setSortBy("createdTime");
        request.setSort("DESC");
        PageRequest pageRequest = PageRequestUtil.composePageRequest(request);

        Page<AppMessageMemberDO> pageResult = appMessageMemberRepository
                .findByOrgIdAndMemberId(orgId, memberId, pageRequest);

        List<AppMessage> bizData = new ArrayList<>();
        pageResult.getContent().forEach(modelDO -> {
            bizData.add(AppModelConverter.convert(modelDO));
        });

        BizPageInfo<AppMessage> bizPageInfo = PageResultUtil.composePageInfo(pageResult);
        bizPageInfo.setBizData(bizData);
        return bizPageInfo;
    }
}