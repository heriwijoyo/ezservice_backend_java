/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberUploadRequest.java, v 0.1 2024‐02‐10 12:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AdminUploadRequest extends ApiRequest {

    private String scene;
    private String role;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}