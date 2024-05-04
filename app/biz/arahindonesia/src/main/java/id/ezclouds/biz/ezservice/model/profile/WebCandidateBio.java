/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.profile;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CandidateBio.java, v 0.1 2023‐12‐10 3:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebCandidateBio extends CandidateBio {

    private int sort;
    private int status;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}