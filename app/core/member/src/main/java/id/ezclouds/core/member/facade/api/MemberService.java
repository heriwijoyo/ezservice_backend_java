/**
 * Ezclouds.id
 * Copyright (c) 2020‐2022 All Rights Reserved.
 */
package id.ezclouds.core.member.facade.api;

import id.ezclouds.core.member.facade.request.MemberCreateRequest;
import id.ezclouds.core.member.facade.result.MemberCreateResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberService.java, v 0.1 2022‐10‐28 12:45 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public interface MemberService {

    MemberCreateResult create(MemberCreateRequest request);
}