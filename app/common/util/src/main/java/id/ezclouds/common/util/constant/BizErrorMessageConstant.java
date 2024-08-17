/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util.constant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizErrorMessageConstant.java, v 0.1 2024‐08‐17 6:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizErrorMessageConstant {

    public static final String MEMBER_LOGIN_MESSAGE_SUCCESS = "LOGIN BERHASIL\n\nSelamat datang di Member Area";
    public static final String MEMBER_LOGIN_MESSAGE_NOT_FOUND = "Nomor HP yang anda masukkan tidak terdaftar, periksa dan coba kembali menggunakan nomor HP yang benar";
    public static final String MEMBER_LOGIN_MESSAGE_SUSPEND = "Akun anda telah di nonaktifkan, silakan hubungi admin sistem kami";
    public static final String MEMBER_LOGIN_MESSAGE_FAILED = "Kata sandi yang anda masukkan salah, coba kembali dengan sandi yang benar";
    public static final String MEMBER_REGISTER_IDEMPOTENT = "Nomor HP yang anda masukkan telah terdaftar, silakan periksa kembali app yang anda masukkan";
    public static final String SESSION_INVALID = "Session aplikasi tidak valid, silakan ulangi kembali proses anda";
    public static final String SESSION_UNAVAILABLE = "Kode verifikasi telah digunakan sebelumnya, silakan ulangi kembali proses anda";
    public static final String SESSION_EXPIRED = "Kode verifikasi telah kadaluarsa, silakan ulangi kembali proses anda";
    public static final String SESSION_VERIFY_FAILED = "Kode verifikasi anda salah, masukkan kembali kode verifikasi yang benar";
    public static final String ILLEGAL_ACTION = "Sistem membatasi akses pada proses tersebut";
    public static final String MEMBER_UNAUTHORIZED = "Anda tidak memiliki akses untuk proses tersebut";
    public static final String MEMBER_REGISTER_SUCCESS = "Pendaftaran anggota berhasil!";
    public static final String MULTIPARTFILE_EMPTY = "File yang anda pilih tidak valid";
    public static final String MULTIPARTFILE_TYPE_UNSUPPORTED = "File yang anda pilih tidak didukung";

}