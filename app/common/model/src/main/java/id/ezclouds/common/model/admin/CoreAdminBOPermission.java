/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.admin;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminBOPermission.java, v 0.1 2024‐02‐11 10:11 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreAdminBOPermission {

    private String orgId;
    private String roleName;
    private String permissionMain;
    private String permissionSub;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermissionMain() {
        return permissionMain;
    }

    public void setPermissionMain(String permissionMain) {
        this.permissionMain = permissionMain;
    }

    public String getPermissionSub() {
        return permissionSub;
    }

    public void setPermissionSub(String permissionSub) {
        this.permissionSub = permissionSub;
    }
}