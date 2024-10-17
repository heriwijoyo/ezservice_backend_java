/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.util;

import id.ezclouds.biz.election.service.request.BizPageRequest;
import id.ezclouds.common.util.StringUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PageRequestUtil.java, v 0.1 2024‐04‐26 3:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class PageRequestUtil {

    public static PageRequest composePageRequest(BizPageRequest request) {
        if (StringUtil.isBlank(request.getSortBy())) {
            return PageRequest.of(request.getPageNumber() - 1, request.getPageSize());
        }

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (StringUtil.equalsIgnoreCase("DESC", request.getSort())) {
            sortDirection = Sort.Direction.DESC;
        }
        return PageRequest.of(request.getPageNumber() - 1, request.getPageSize(), Sort.by(sortDirection, request.getSortBy()));
    }
}