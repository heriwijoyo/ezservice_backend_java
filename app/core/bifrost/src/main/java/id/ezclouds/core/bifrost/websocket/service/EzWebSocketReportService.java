/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.websocket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.BizReportRealtimeService;
import id.ezclouds.common.facade.biz.report.BizReportAccumulateAreaService;
import id.ezclouds.common.facade.biz.report.BizReportOverallService;
import id.ezclouds.common.model.area.District;
import id.ezclouds.common.model.auth.AuthSession;
import id.ezclouds.common.model.biz.report.BizReportArea;
import id.ezclouds.common.model.broker.event.OverallReportChangeEvent;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.websocket.WebSocketData;
import id.ezclouds.common.model.websocket.WebSocketEvent;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.core.bifrost.websocket.model.DataTopic;
import id.ezclouds.core.bifrost.websocket.model.SessionIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzWebSocketReportService.java, v 0.1 2024‐08‐31 10:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzWebSocketReportService extends TextWebSocketHandler {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private BizReportRealtimeService bizReportRealtimeService;

    @Autowired
    private BizReportOverallService bizReportOverallService;

    @Autowired
    private BizReportAccumulateAreaService bizReportAccumulateAreaService;

    private Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private Map<String, SessionIdentity> identityMap = new ConcurrentHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Async
    @EventListener
    public void handleEzCommonEventData(OverallReportChangeEvent event) {
        if (identityMap.isEmpty()) {
            return;
        }

        List<String> availSessionIds = new ArrayList<>();
        for (Map.Entry<String, SessionIdentity> identityEntry : identityMap.entrySet()) {
            if (StringUtil.equals(identityEntry.getValue().getOrgId(), event.getOrgId())) {
                availSessionIds.add(identityEntry.getKey());
            }
        }
        if (availSessionIds.isEmpty()) {
            return;
        }

        List<BizReportOverall> reportOverall = bizReportOverallService.getReportOverall(event.getOrgId());
        for (String sessionId : availSessionIds) {
            if (sessionMap.get(sessionId) != null) {
                SessionIdentity identity = identityMap.get(sessionId);

                if (identity.getTopics().contains(DataTopic.OVERALL.getCode())) {
                    sessionSendMessage(sessionMap.get(sessionId), WebSocketEvent.DATA_RESULT, DataTopic.OVERALL, reportOverallToMap(reportOverall));
                }
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionSendMessage(session, WebSocketEvent.SESSION_AUTH_REQUIRED, DataTopic.NON_DATA, "");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessionMap.remove(session.getId());
        identityMap.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        try {
            WebSocketData data = objectMapper.readValue(message.getPayload(), WebSocketData.class);

            WebSocketEvent event = WebSocketEvent.getByCode(data.getEvent());
            switch (event) {
                case PERFORM_AUTH_CLIENT:
                    performAuthClient(session, data.getPayload());
                    break;

                case DATA_REQUEST:
                    performDataRequest(session, data.getTopic(), data.getPayload());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void performDataRequest(WebSocketSession session, String topic, Object payload) {
        SessionIdentity identity = identityMap.get(session.getId());

        if (sessionMap.get(session.getId()) != null && identity != null) {
            if (!identity.getTopics().contains(topic)) {
                identity.getTopics().add(topic);
            }

            String orgId = identity.getOrgId();
            DataTopic dataTopic = DataTopic.getByCode(topic);
            if (dataTopic == DataTopic.OVERALL) {
                List<BizReportOverall> reportOverall = bizReportOverallService.getReportOverall(orgId);
                sessionSendMessage(session, WebSocketEvent.DATA_RESULT, DataTopic.OVERALL, reportOverallToMap(reportOverall));
            }
            if (dataTopic == DataTopic.VOTER_BASE_AREA) {
                BizReportArea reportArea;
                if (payload instanceof String && StringUtil.isNotBlank((String) payload)) {
                    String districtId = (String) payload;
                    District district = new District(districtId, "1802", "");
                    reportArea = bizReportAccumulateAreaService.getReportArea(orgId, district);
                }
                else {
                    reportArea = bizReportAccumulateAreaService.getReportArea(orgId);
                }
                sessionSendMessage(session, WebSocketEvent.DATA_RESULT, DataTopic.VOTER_BASE_AREA, reportArea);
            }
        }
    }

    private void performAuthClient(WebSocketSession session, Object payload) {
        if (payload instanceof String) {
            String sessionId = (String) payload;
            try {
                AuthSession authSession = authAdminService.authorizeWebPublicSession(sessionId);

                SessionIdentity identity = new SessionIdentity(session.getId(), authSession.getOrgId(), authSession.getOrgCode());

                sessionMap.put(session.getId(), session);
                identityMap.put(session.getId(), identity);
                sessionSendMessage(session, WebSocketEvent.SESSION_AUTH_RESULT, DataTopic.NON_DATA, "SUCCESS");

            } catch (Exception exception) {
                exception.printStackTrace();
                sessionSendMessage(session, WebSocketEvent.SESSION_AUTH_RESULT, DataTopic.NON_DATA, "FAILED:"+ ExceptionUtil.getStackTrace(exception));
                sessionClose(session);
            }
        }
    }

    private void sessionSendMessage(WebSocketSession session, WebSocketEvent event, DataTopic dataTopic,  Object payload) {
        try {
            session.sendMessage(composeMessage(event, dataTopic, payload));
        } catch (Exception ignored) {}
    }

    private void sessionClose(WebSocketSession session) {
        try {
            sessionMap.remove(session.getId());
            session.close();
        } catch (Exception ignored) {}
    }

    private TextMessage composeMessage(WebSocketEvent event, DataTopic dataTopic, Object payload) {
        String strData = StringUtil.EMPTY;
        try {
            WebSocketData webSocketData = new WebSocketData();
            webSocketData.setEvent(event.getCode());
            webSocketData.setTopic(dataTopic.getCode());
            webSocketData.setPayload(payload);

            strData = objectMapper.writeValueAsString(webSocketData);
        } catch (Exception ignored) {}

        return new TextMessage(strData);
    }

    private Map<String, Integer> reportOverallToMap(List<BizReportOverall> reportOveralls) {
        Map<String, Integer> reportMap = new HashMap<>();
        for (BizReportOverall reportOverall : reportOveralls) {
            reportMap.put(reportOverall.getKeyId(), reportOverall.getCount());
        }
        return reportMap;
    }
}