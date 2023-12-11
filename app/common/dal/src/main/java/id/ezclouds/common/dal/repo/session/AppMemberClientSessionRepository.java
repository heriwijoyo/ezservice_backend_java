/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.repo.session;

import id.ezclouds.common.dal.model.AppMemberClientSessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMemberClientSessionRepository.java, v 0.1 2023‐12‐11 11:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppMemberClientSessionRepository extends JpaRepository<AppMemberClientSessionDO, String> {

    @Query(value = "INSERT INTO app_member_client_session (session_id, org_id, app_id, client_id, member_id, device_id, created_time, expiry_time) VALUES (:sessionId, :orgId, :appId, :clientId, :memberId, :deviceId, :createdTime, :expiryTime)", nativeQuery = true)
    @Modifying
    void insertActiveSession(
            @Param("sessionId") String sessionId,
            @Param("orgId") String orgId,
            @Param("appId") String appId,
            @Param("clientId") String clientId,
            @Param("memberId") String memberId,
            @Param("deviceId") String deviceId,
            @Param("createdTime") String createdTime,
            @Param("expiryTime") String expiryTime
    );
}