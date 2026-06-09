/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreAdminBOPermissionDO.java, v 0.1 2024‐02‐11 9:57 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_admin_bo_permission")
public class EzCoreAdminBOPermissionDO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "permission_main")
    private String permissionMain;

    @Column(name = "permission_sub")
    private String permissionSub;

    @Column(name = "status")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}