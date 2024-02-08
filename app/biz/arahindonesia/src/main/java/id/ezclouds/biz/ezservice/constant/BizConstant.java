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

    public static final class Key {
        public static final String UPDATE_PASSWORD_MODE_MEMBER_SESSION = "MEMBER_SESSION";
        public static final String UPDATE_PASSWORD_MODE_RESET_SESSION = "RESET_SESSION";
        public static final String FORCED_UPDATE_PASSWORD = "FORCED_UPDATE_PASSWORD";
        public static final String COMMON_SESSION_ID = "COMMON_SESSION_ID";
    }

    public static final class MemberFlag {
        public static final String NEED_UPDATE_PASSWORD = "NEED_UPDATE_PASSWORD";
    }

    public static final class Message {
        public static final String UPDATE_PASSWORD_SUCCESS = "Kata sandi berhasil di perbarui. Silakan LOGIN kembali menggunakan sandi baru anda";
        public static final String COMMON_SESSION_VERIFY_SUCCESS = "Verifikasi kode OTP berhasil";
    }

    public static final class Auth {
        public static final String COMMON_SESSION_SCENE_RESET_MEMBER_PASSWORD = "RESET_MEMBER_PASSWORD";
        public static final String COMMON_SESSION_SCENE_VERIFY_PHONE = "VERIFY_PHONE";
        public static final String COMMON_SESSION_VERIFY_STRATEGY_WHATSAPP = "WHATSAPP";
    }

    public static final class TemplateKey {
        public static final String WA_RESET_PASS_VERIFY_CODE = "WA_RESET_PASS_VERIFY_CODE";
        public static final String VERIFY_CODE = "VERIFY_CODE";
        public static final String EXPIRY_LABEL = "EXPIRY_LABEL";
    }

    public static List<String> commonSessionSceneAllowed() {
        List<String> allowed = new ArrayList<>();
        allowed.add(BizConstant.Auth.COMMON_SESSION_SCENE_RESET_MEMBER_PASSWORD);
        allowed.add(Auth.COMMON_SESSION_SCENE_VERIFY_PHONE);
        return allowed;
    }
}