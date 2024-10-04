/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.member.CoreMemberExtension;
import id.ezclouds.core.dal.member.dataobject.CoreMemberExtensionDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtensionConverter.java, v 0.1 2024‐10‐05 3:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberExtensionConverter extends CommonDOModelConverter<CoreMemberExtensionDO, CoreMemberExtension> {

    @Override
    protected CoreMemberExtension safeConvertQuery(CoreMemberExtensionDO dataObject) {

        return null;
    }

    @Override
    protected CoreMemberExtensionDO safeConvertStore(CoreMemberExtension model) {
        return null;
    }
}