/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizConstant.java, v 0.1 2024‐02‐04 10:01 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizConstant {

    public static final class ExtKey {
        public static final String UPDATE_PASSWORD_MODE_MEMBER_SESSION = "MEMBER_SESSION";
        public static final String UPDATE_PASSWORD_MODE_RESET_SESSION = "RESET_SESSION";
        public static final String FORCED_UPDATE_PASSWORD = "FORCED_UPDATE_PASSWORD";
        public static final String COMMON_SESSION_ID = "COMMON_SESSION_ID";
        public static final String NICKNAME = "NICKNAME";
        public static final String HAS_SUB_ORG = "HAS_SUB_ORG";
    }

    public static final class MemberFlag {
        public static final String NEED_UPDATE_PASSWORD = "NEED_UPDATE_PASSWORD";
    }

    public static final class Message {
        public static final String UPDATE_PASSWORD_SUCCESS = "Kata sandi berhasil di perbarui. Silakan LOGIN kembali menggunakan sandi baru anda";
        public static final String COMMON_SESSION_VERIFY_SUCCESS = "Verifikasi kode OTP berhasil";
        public static final String SUCCESS_LOGOUT = "Logout Berhasil";
        public static final String SUCCESS_COMMON = "Proses Berhasil";
    }

    public static final class Auth {
        public static final String COMMON_SESSION_SCENE_RESET_MEMBER_PASSWORD = "RESET_MEMBER_PASSWORD";
        public static final String COMMON_SESSION_SCENE_VERIFY_PHONE = "VERIFY_PHONE";
        public static final String COMMON_SESSION_VERIFY_STRATEGY_WHATSAPP = "WHATSAPP";
    }

    public static final class TemplateKey {
        public static final String WA_RESET_PASS_VERIFY_CODE = "WA_RESET_PASS_VERIFY_CODE";
        public static final String WA_MEMBER_CREATE_PASSWORD = "WA_MEMBER_CREATE_PASSWORD";
        public static final String VERIFY_CODE = "VERIFY_CODE";
        public static final String EXPIRY_LABEL = "EXPIRY_LABEL";
        public static final String APP_NAME = "APP_NAME";
        public static final String PHONE = "PHONE";
        public static final String PASSWORD = "PASSWORD";
        public static final String APP_DOWNLOAD_URL = "APP_DOWNLOAD_URL";
    }

    public static List<String> commonSessionSceneAllowed() {
        List<String> allowed = new ArrayList<>();
        allowed.add(BizConstant.Auth.COMMON_SESSION_SCENE_RESET_MEMBER_PASSWORD);
        allowed.add(Auth.COMMON_SESSION_SCENE_VERIFY_PHONE);
        return allowed;
    }
}