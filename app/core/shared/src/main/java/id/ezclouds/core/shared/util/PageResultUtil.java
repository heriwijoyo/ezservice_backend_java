/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.util;

import id.ezclouds.core.shared.converter.PageResultConverter;
import id.ezclouds.core.shared.result.BizPageInfo;
import id.ezclouds.core.shared.result.PageResult;
import org.springframework.data.domain.Page;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PageResultUtil.java, v 0.1 2024‐05‐07 12:09 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class PageResultUtil {

    public static BizPageInfo composePageInfo(Page<?> pageResult) {
        BizPageInfo bizPageInfo = new BizPageInfo();
        bizPageInfo.setPageNumber(pageResult.getPageable().getPageNumber() + 1);
        bizPageInfo.setPageSize(pageResult.getPageable().getPageSize());
        bizPageInfo.setTotalPage(pageResult.getTotalPages());
        bizPageInfo.setNumberRecord(pageResult.getNumberOfElements());
        bizPageInfo.setTotalRecord((int)pageResult.getTotalElements());
        bizPageInfo.setHasNext(pageResult.hasNext());
        return bizPageInfo;
    }

    public static <T> PageResult<T> composeFromPageInfo(BizPageInfo<T> pageInfo) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNumber(pageInfo.getPageNumber());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setNumberRecord(pageInfo.getNumberRecord());
        pageResult.setTotalRecord(pageInfo.getTotalRecord());
        pageResult.setTotalPage(pageInfo.getTotalPage());
        pageResult.setHasNext(pageInfo.isHasNext());
        pageResult.setHasPrevious(pageInfo.getPageNumber() > 1);
        pageResult.setData(pageInfo.getBizData());
        return pageResult;
    }

    public static <I, O> PageResult<O> convert(PageResult<I> inputResult, PageResultConverter<I, O> converter) {
        PageResult<O> pageResult = new PageResult<>();
        pageResult.setPageNumber(inputResult.getPageNumber());
        pageResult.setPageSize(inputResult.getPageSize());
        pageResult.setNumberRecord(inputResult.getNumberRecord());
        pageResult.setTotalRecord(inputResult.getTotalRecord());
        pageResult.setTotalPage(inputResult.getTotalPage());
        pageResult.setHasNext(inputResult.isHasNext());
        pageResult.setHasPrevious(inputResult.isHasPrevious());
        pageResult.setData(converter.convert(inputResult.getData()));
        return pageResult;
    }

    public static <I, O> PageResult<O> convertFindResult(Page<I> findResult, PageResultConverter<I, O> converter) {
        PageResult<O> pageResult = new PageResult<>();
        pageResult.setPageNumber(findResult.getPageable().getPageNumber() + 1);
        pageResult.setPageSize(findResult.getPageable().getPageSize());
        pageResult.setNumberRecord(findResult.getNumberOfElements());
        pageResult.setTotalPage(findResult.getTotalPages());
        pageResult.setTotalRecord((int)findResult.getTotalElements());
        pageResult.setHasNext(findResult.hasNext());
        pageResult.setHasPrevious(findResult.hasPrevious());
        pageResult.setData(converter.convert(findResult.getContent()));
        return pageResult;

    }
}