/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request;

import java.io.InputStream;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: FileStreamRequest.java, v 0.1 2024‐08‐11 4:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class FileStreamRequest {

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}