/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.util;

import id.ezclouds.core.member.dataobject.CoreMemberDO;
import id.ezclouds.core.member.dataobject.CoreMemberExtensionDO;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberConverter.java, v 0.1 2023‐12‐31 11:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberConverter {

    public static CoreMember convert(CoreMemberDO memberDO) {
        if (memberDO == null) { return null; }
        CoreMember member = new CoreMember();
        return member;
    }

    public static CoreMemberDO convert(CoreMember coreMember) {
        if (coreMember == null) { return null; }
        CoreMemberDO memberDO = new CoreMemberDO();
        return memberDO;
    }

    public static CoreMemberExtension convert(CoreMemberExtensionDO extensionDO) {
        if (extensionDO == null) { return null; }
        CoreMemberExtension extension = new CoreMemberExtension();
        return extension;
    }

    public static CoreMemberExtensionDO convert(CoreMemberExtension extension) {
        if (extension == null) { return null; }
        CoreMemberExtensionDO extensionDO = new CoreMemberExtensionDO();
        return extensionDO;
    }
}