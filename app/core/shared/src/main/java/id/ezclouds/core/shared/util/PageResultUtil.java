/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.util;

import id.ezclouds.core.shared.result.BizPageInfo;
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
}