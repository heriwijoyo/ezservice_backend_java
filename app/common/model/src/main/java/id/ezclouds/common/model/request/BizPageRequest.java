/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request;

import id.ezclouds.common.model.constant.PageSort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizPageRequest.java, v 0.1 2024‐08‐10 11:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizPageRequest extends BizRequest {

    private String orgId;
    private int pageNumber;
    private int pageSize;
    private String searchScene;
    private String searchKeyword;
    private PageSort pageSort;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchScene() {
        return searchScene;
    }

    public void setSearchScene(String searchScene) {
        this.searchScene = searchScene;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public PageSort getPageSort() {
        return pageSort;
    }

    public void setPageSort(PageSort pageSort) {
        this.pageSort = pageSort;
    }

    public PageRequest toPageRequest() {
        if (pageSort == null || pageSort == PageSort.UNKNOWN) {
            return PageRequest.of(pageNumber - 1, pageSize);
        }

        switch (pageSort) {
            case NEWEST:
            case LATEST:
                return PageRequest.of(pageNumber - 1, pageSize, Sort.by(pageSort.getSortDirection(), pageSort.getSortKey()));
            default:
                return PageRequest.of(pageNumber - 1, pageSize);
        }
    }
}