/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util;

import java.util.Random;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: RandomUtil.java, v 0.1 2024‐04‐03 11:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class RandomUtil {

    public static String generateNumberCode(int length) {
        if (length < 2) {
            length = 2;
        }
        int exponent = length - 1;
        int addition = 1;
        for (int i = 0; i < exponent; i++) {
            addition = addition * 10;
        }
        int bound = 9 * addition;
        int randomNumber = new Random().nextInt(bound) + addition;
        return String.valueOf(randomNumber);
    }
}