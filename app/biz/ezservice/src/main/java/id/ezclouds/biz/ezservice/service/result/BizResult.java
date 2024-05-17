/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.result;

import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.shared.result.BizPageInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizResult.java, v 0.1 2023‐12‐31 2:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizResult implements Serializable {

    private boolean success;
    private EzErrorCode errorCode;
    private String errorLocation;
    private String errorMessage;
    private Object object;

    private BizPageInfo<?> bizPageInfo;

    public BizResult() {
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public EzErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(EzErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorLocation() {
        return errorLocation;
    }

    public void setErrorLocation(String errorLocation) {
        this.errorLocation = errorLocation;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public BizPageInfo<?> getBizPageInfo() {
        return bizPageInfo;
    }

    public void setBizPageInfo(BizPageInfo<?> bizPageInfo) {
        this.bizPageInfo = bizPageInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}