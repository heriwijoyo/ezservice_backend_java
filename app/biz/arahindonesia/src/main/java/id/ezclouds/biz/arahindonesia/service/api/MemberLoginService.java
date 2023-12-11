/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.api;

import id.ezclouds.biz.arahindonesia.model.login.MemberLoginResult;
import id.ezclouds.biz.arahindonesia.service.authentication.AuthenticationService;
import id.ezclouds.core.shared.model.MemberLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberLoginService.java, v 0.1 2023‐12‐11 3:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class MemberLoginService {

    @Autowired
    private AuthenticationService authenticationService;

    public MemberLoginResult loginMember(MemberLogin memberLogin) {
        authenticationService.authenticate(memberLogin);

        return new MemberLoginResult();
    }
}