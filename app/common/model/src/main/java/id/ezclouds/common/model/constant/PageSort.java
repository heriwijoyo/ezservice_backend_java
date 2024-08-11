/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.constant;

import org.springframework.data.domain.Sort;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PageSort.java, v 0.1 2024‐08‐11 12:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum PageSort {

    NEWEST("NEWEST", "createdTime", Sort.Direction.DESC),
    LATEST("LATEST", "createdTime", Sort.Direction.ASC),
    UNKNOWN("UNKNOWN", "UNKNOWN", null),
    ;
    private final String code;
    private final String sortKey;
    private final Sort.Direction sortDirection;

    PageSort(String code, String sortKey, Sort.Direction sortDirection) {
        this.code = code;
        this.sortKey = sortKey;
        this.sortDirection = sortDirection;
    }

    public static PageSort getByCode(String code) {
        for (PageSort pageSort : values()) {
            if (pageSort.code.equals(code)) {
                return pageSort;
            }
        }
        return UNKNOWN;
    }

    public String getSortKey() {
        return sortKey;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }
}