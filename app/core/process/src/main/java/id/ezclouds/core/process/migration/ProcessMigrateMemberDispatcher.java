/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.migration;

import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.util.facade.BeanFacadeUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ProcessMigrateMemberDispatcher.java, v 0.1 2024‐10‐06 4:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ProcessMigrateMemberDispatcher {

    public void migrateMember(CoreMember coreMember) {
        BeanFacadeUtil
                .getBean(InnerProcessMigrateMember.class)
                .migrateMember(coreMember);
    }
}