/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.converter;

import id.ezclouds.common.model.auth.AuthAppClient;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.auth.dataobject.EzAuthAppClientDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthAppClientQueryConverter.java, v 0.1 2024‐08‐13 5:39 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAuthAppClientQueryConverter extends TemplateModelConverter<EzAuthAppClientDO, AuthAppClient> {

    @Override
    protected AuthAppClient safeConvert(EzAuthAppClientDO input) {
        AuthAppClient authAppClient = new AuthAppClient();
        authAppClient.setOrgId(input.getOrgId());
        authAppClient.setAppId(input.getAppId());
        authAppClient.setClientId(input.getClientId());
        authAppClient.setClientSecret(input.getClientSecret());
        return authAppClient;
    }
}