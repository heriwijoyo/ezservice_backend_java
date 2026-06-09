/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.file;

import id.ezclouds.common.model.file.OrgFileResolver;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: OrgFileResolverImpl.java, v 0.1 2024‐11‐03 8:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class OrgFileResolverImpl implements OrgFileResolver {

    private final String uploadRootDir;
    private final String orgId;

    public OrgFileResolverImpl(String uploadRootDir, String orgId) {
        this.uploadRootDir = uploadRootDir;
        this.orgId = orgId;
    }

    @Override
    public String getOrgRootDir() {
        return uploadRootDir + "/" + orgId;
    }
}