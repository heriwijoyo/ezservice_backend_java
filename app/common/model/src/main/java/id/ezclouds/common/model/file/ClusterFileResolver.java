/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.file;

import java.nio.file.Path;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ClusterFileResolver.java, v 0.1 2024‐11‐03 8:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface ClusterFileResolver {

    Path getMemberReportVoterXlsxPath(String memberId);
}