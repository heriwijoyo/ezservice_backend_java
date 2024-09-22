/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.request;

import id.ezclouds.biz.election.enums.BizImportScene;
import id.ezclouds.biz.election.service.request.admin.BizAdminUploadRequest;

import java.nio.file.Path;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizDataImportRequest.java, v 0.1 2024‐07‐05 1:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizDataImportRequest extends BizAdminUploadRequest {

    private BizImportScene importScene;
    private Path filePath;
    private String orgId;
    private String subOrgId;
    private String fileId;
    private String fileName;

    public BizImportScene getImportScene() {
        return importScene;
    }

    public void setImportScene(BizImportScene importScene) {
        this.importScene = importScene;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSubOrgId() {
        return subOrgId;
    }

    public void setSubOrgId(String subOrgId) {
        this.subOrgId = subOrgId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}