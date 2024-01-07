/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BoolState.java, v 0.1 2024‐01‐07 5:03 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BoolState {

    public static int getState(boolean bool) {
        return bool ? 1 : 0;
    }

    public static boolean getBool(int state) {
        return state == 1;
    }
}