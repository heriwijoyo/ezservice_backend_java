/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: HashUtil.java, v 0.1 2023‐12‐11 10:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class HashUtil {

    public static String createHash(String... inputs) {
        return createHash(StringUtil.concateAll(inputs));
    }

    public static String createHash(String input) {
        String hashText = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            hashText = convertToHex(messageDigest);
        } catch (Exception e) {}

        if (hashText != null) {
            return hashText;
        }
        return UUID.randomUUID().toString().replaceAll("_", "");
    }

    private static String convertToHex(byte[] messageDigest) {
        BigInteger bigInt = new BigInteger(1, messageDigest);
        String hexText = bigInt.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }
}