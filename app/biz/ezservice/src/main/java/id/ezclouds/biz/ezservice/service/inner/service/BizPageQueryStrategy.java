/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.inner.service;

import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.core.shared.result.BizPageInfo;
import id.ezclouds.common.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizPageQueryStrategy.java, v 0.1 2024‐04‐26 12:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizPageQueryStrategy<T, ID, M> {

    public BizPageInfo pageQuery(JpaRepository<T, ID> jpaRepository, BizPageRequest request, ResultConverter<T, M> converter) {

        PageRequest pageRequest = buildPageRequest(request.getPageNumber(), request.getPageSize(), request.getSortBy(), request.getSort());
        Page<T> pageResult = jpaRepository.findAll(pageRequest);

        BizPageInfo bizPageInfo = new BizPageInfo();
        bizPageInfo.setPageNumber(request.getPageNumber());
        bizPageInfo.setPageSize(request.getPageSize());
        bizPageInfo.setTotalPage(pageResult.getTotalPages());
        bizPageInfo.setNumberRecord(pageResult.getNumberOfElements());
        bizPageInfo.setTotalRecord((int)pageResult.getTotalElements());
        bizPageInfo.setHasNext(pageResult.hasNext());

        List<Object> bizData = new ArrayList<>();
        for (T modelDO : pageResult.getContent()) {
            bizData.add(converter.convert(modelDO));
        }
        bizPageInfo.setBizData(bizData);
        return bizPageInfo;
    }

    private PageRequest buildPageRequest(int page, int size, String sortBy, String sort) {
        if (StringUtil.isBlank(sortBy)) {
            return PageRequest.of(page - 1, size);
        }

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (StringUtil.equalsIgnoreCase("DESC", sort)) {
            sortDirection = Sort.Direction.DESC;
        }
        return PageRequest.of(page - 1, size, Sort.by(sortDirection, sortBy));
    }

    public interface ResultConverter<T, M> {
        M convert(T modelDO);
    }
}