/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.file;

import id.ezclouds.common.model.file.ClusterFileResolver;
import id.ezclouds.common.model.file.OrgFileResolver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ClusterFileResolverImpl.java, v 0.1 2024‐11‐03 8:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ClusterFileResolverImpl implements ClusterFileResolver {

    private static final String REPORT_VOTER_XLSX_PREFIX = "REPORT_VOTER_XLSX_";

    private final OrgFileResolver orgFileResolver;
    private final String clusterId;

    public ClusterFileResolverImpl(String uploadRootDir, String orgId, String clusterId) {
        this.orgFileResolver = new OrgFileResolverImpl(uploadRootDir, orgId);
        this.clusterId = clusterId;
        initClusterDir();
    }

    @Override
    public Path getMemberReportVoterXlsxPath(String memberId) {
        String fileName = REPORT_VOTER_XLSX_PREFIX + memberId + ".xlsx";
        return Paths.get(getClusterPath().toString(), fileName)
                .toAbsolutePath()
                .normalize();
    }

    private Path getClusterPath() {
        return Paths.get(orgFileResolver.getOrgRootDir(), PublicFileInitializer.DIR_CLUSTER, clusterId)
                .toAbsolutePath()
                .normalize();
    }

    private void initClusterDir() {
        if (Files.notExists(getClusterPath())) {
            try {
                Files.createDirectory(getClusterPath());
            } catch (Exception e) {
                System.out.println("Directory Created Failed: " + getClusterPath().toString());
            }
        }
    }
}