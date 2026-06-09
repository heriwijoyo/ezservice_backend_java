/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CommonWebDigestLog.java, v 0.1 2024‐02‐09 1:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CommonWebDigestLog extends BaseWebDigestLog {

    public CommonWebDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigestMessage(String message) {
        setDigestMessage(message);
    }
}