/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.request;

import id.ezclouds.biz.ezservice.service.app.model.AppAsyncScene;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppAsyncRequest.java, v 0.1 2024‐05‐10 10:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppAsyncRequest {

    private AppAsyncScene appAsyncScene;
    private Object data;

    public AppAsyncScene getAppAsyncScene() {
        return appAsyncScene;
    }

    public void setAppAsyncScene(AppAsyncScene appAsyncScene) {
        this.appAsyncScene = appAsyncScene;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}