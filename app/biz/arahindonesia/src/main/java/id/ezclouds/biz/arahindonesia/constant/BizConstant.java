/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.constant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizConstant.java, v 0.1 2024‐02‐04 10:01 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizConstant {
    public static final String UPDATE_PASSWORD_MODE_MEMBER_SESSION = "MEMBER_SESSION";
    public static final String UPDATE_PASSWORD_MODE_RESET_SESSION = "RESET_SESSION";

    public static final class MemberFlags {
        public static final String NEED_UPDATE_PASSWORD = "NEED_UPDATE_PASSWORD";
    }

    public static final class Message {
        public static final String UPDATE_PASSWORD_SUCCESS = "Kata sandi berhasil di perbarui. Silakan LOGIN kembali menggunakan sandi baru anda";
    }
}