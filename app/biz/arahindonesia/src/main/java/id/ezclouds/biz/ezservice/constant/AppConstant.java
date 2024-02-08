/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.constant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConstant.java, v 0.1 2023‐12‐10 12:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppConstant {

    public static final String APP_VERSION_NAME_TAG = "APP_VERSION_NAME_TAG";
    public static final String APP_UPDATE_TITLE = "Informasi Pembaruan";
    public static final String APP_UPDATE_MESSAGE = "Pembaruan aplikasi versi "+ APP_VERSION_NAME_TAG +" telah tersedia. Perbarui aplikasi anda untuk mendapatkan keuntungan maksimal dari semua fitur";

    public static final String IMAGE_SLIDE_SECTION_HOME = "HOME_BANNER";
    public static final String IMAGE_SLIDE_HOME_POSTER = "HOME_POSTER";
    public static final String IMAGE_SLIDE_PORTFOLIO = "PORTFOLIO";

    public static final String TMP_MID_BANNER_URL = "http://ezservice.id/uploads/hb_006.jpeg";

    public static final int HIGHLIGHTED_NEWS_LIMIT = 3;
    public static final int COMMON_STATUS_ACTIVE = 1;

    public static final String PHONE_PREFIX = "62";

    public static final String MESSAGE_SYSTEM_ABNORMAL = "Terjadi kesalahan pada sistem, silakan hubungi admin sistem kami";
    public static final String MEMBER_LOGIN_MESSAGE_SUCCESS = "LOGIN BERHASIL\n\nSelamat datang di Member Area Sistem RJL";
    public static final String MEMBER_LOGIN_MESSAGE_NOT_FOUND = "Nomor HP yang anda masukkan tidak terdaftar, periksa dan coba kembali menggunakan nomor HP yang benar";
    public static final String MEMBER_LOGIN_MESSAGE_SUSPEND = "Akun anda telah di nonaktifkan, silakan hubungi admin sistem kami";
    public static final String MEMBER_LOGIN_MESSAGE_FAILED = "Kata sandi yang anda masukkan salah, coba kembali dengan sandi yang benar";

    public static final String MEMBER_REGISTER_IDEMPOTENT = "Nomor HP yang anda masukkan telah terdaftar, silakan periksa kembali dataservice yang anda masukkan";

    public static final int APP_V2_START_VERSION_NO = 15;

    public static final class BizMessage {
        public static final String SESSION_INVALID = "Session aplikasi tidak valid, silakan ulangi kembali proses anda";
        public static final String SESSION_UNAVAILABLE = "Kode verifikasi telah digunakan sebelumnya, silakan ulangi kembali proses anda";
        public static final String SESSION_EXPIRED = "Kode verifikasi telah kadaluarsa, silakan ulangi kembali proses anda";
        public static final String SESSION_VERIFY_FAILED = "Kode verifikasi anda salah, masukkan kembali kode verifikasi yang benar";
        public static final String ILLEGAL_ACTION = "Sistem membatasi akses pada proses tersebut";
    }

    public static final class ExtKey {
        public static final String SOURCE_ID = "SOURCE_ID";
    }

    public static final class Annotation {
        public static final String AVATAR_URL = "AVATAR_URL";
    }
}