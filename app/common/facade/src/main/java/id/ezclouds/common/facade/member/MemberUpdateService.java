/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.member;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberUpdateService.java, v 0.1 2024‐08‐12 7:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface MemberUpdateService {
    void updateRoles(String memberId, String roles);
}