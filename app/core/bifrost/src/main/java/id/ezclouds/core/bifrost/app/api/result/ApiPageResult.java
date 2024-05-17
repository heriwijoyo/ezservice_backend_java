/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.result;

import id.ezclouds.core.shared.result.BizPageInfo;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiPageResult.java, v 0.1 2024‐04‐25 10:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ApiPageResult<T> extends ApiResult<T> {

    private BizPageInfo bizPageInfo;

    public ApiPageResult() {
        setSuccess(false);
    }

    public BizPageInfo getBizPageInfo() {
        return bizPageInfo;
    }

    public void setBizPageInfo(BizPageInfo bizPageInfo) {
        this.bizPageInfo = bizPageInfo;
    }
}