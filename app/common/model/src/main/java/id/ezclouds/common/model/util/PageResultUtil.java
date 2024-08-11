/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import id.ezclouds.common.model.result.PageResult;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PageResultUtil.java, v 0.1 2024‐08‐11 6:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class PageResultUtil {

    public static <I, O> PageResult<O> convertFindResult(Page<I> findResult, ModelConverter<I, O> converter) {
        PageResult<O> pageResult = new PageResult<>();
        pageResult.setPageNumber(findResult.getPageable().getPageNumber() + 1);
        pageResult.setPageSize(findResult.getPageable().getPageSize());
        pageResult.setNumberRecord(findResult.getNumberOfElements());
        pageResult.setTotalPage(findResult.getTotalPages());
        pageResult.setTotalRecord((int)findResult.getTotalElements());
        pageResult.setHasNext(findResult.hasNext());
        pageResult.setHasPrevious(findResult.hasPrevious());

        List<O> outputList = new ArrayList<>();
        if (findResult.getContent() != null) {
            findResult.getContent().forEach(input -> {
                outputList.add(converter.convert(input));
            });
        }

        pageResult.setData(outputList);
        return pageResult;
    }

    public static <O, A> void adjustPageResult(PageResult<O> pageResult, ModelAdjuster<O> adjuster) {
        for (O origin : pageResult.getData()) {
            adjuster.adjust(origin);
        }
    }
}