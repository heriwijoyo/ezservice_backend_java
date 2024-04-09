/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import id.ezclouds.biz.ezservice.enums.BizUploadScene;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMultipartRequest.java, v 0.1 2024‐02‐08 2:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class BizMultipartRequest extends BizOptionalMultipartRequest {

    protected final List<String> imageTypes = Arrays.asList("image/png", "images/png", "image/jpg", "images/jpg", "image/jpeg", "images/jpeg");

    protected final List<String> onlyPNG = Collections.singletonList("image/png");

    protected final List<String> onlyAPK = Collections.singletonList("application/vnd.android.package-archive");

    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public abstract BizUploadScene getScene();

    protected abstract void presetScene(BizUploadScene scene);

    protected abstract List<BizUploadScene> getSupportedScene();

    protected abstract List<String> getSupportedContentType();

    public void validateMultipartRequest() throws EzErrorException {
        presetScene(getScene());
        AssertUtil.isNotTrue(getScene() == BizUploadScene.UNKNOWN, EzErrorCode.UPLOAD_SCENE_EMPTY);
        AssertUtil.isTrue(!getSupportedScene().isEmpty(), EzErrorCode.UPLOAD_SCENE_NOT_ALLOWED);
        AssertUtil.isTrue(getSupportedScene().contains(getScene()), EzErrorCode.UPLOAD_SCENE_NOT_ALLOWED);

        if (isMultipartFileRequired()) {
            AssertUtil.notNull(multipartFile, EzErrorCode.MULTIPARTFILE_EMPTY);
            AssertUtil.isTrue(multipartFile.getSize() > 0, EzErrorCode.MULTIPARTFILE_EMPTY);
            AssertUtil.notNull(getSupportedContentType(), EzErrorCode.MULTIPARTFILE_TYPE_UNDEFINED);
            AssertUtil.isTrue(getSupportedContentType().size() > 0, EzErrorCode.MULTIPARTFILE_TYPE_UNDEFINED);
            AssertUtil.isTrue(getSupportedContentType().contains(multipartFile.getContentType()), EzErrorCode.MULTIPARTFILE_TYPE_UNSUPPORTED);
        }
    }

    public String getFileExtension() {
        if (multipartFile != null && multipartFile.getSize() > 0) {
            return StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        }
        return "";
    }
}