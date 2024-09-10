/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.BizReportRealtimeService;
import id.ezclouds.common.facade.broker.BrokerDataEvent;
import id.ezclouds.common.facade.broker.BrokerDataExchangeService;
import id.ezclouds.common.model.auth.AuthSession;
import id.ezclouds.common.model.broker.BrokerDataTopic;
import id.ezclouds.common.model.websocket.WebSocketData;
import id.ezclouds.common.model.websocket.WebSocketEvent;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzWebSocketReportService.java, v 0.1 2024‐08‐31 10:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzWebSocketReportService extends TextWebSocketHandler implements InitializingBean, BrokerDataEvent {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private BrokerDataExchangeService brokerDataExchangeService;

    @Autowired
    private BizReportRealtimeService bizReportRealtimeService;


    private Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private Map<String, SessionIdentity> identityMap = new ConcurrentHashMap<>();
    private Map<String, Map<String, Integer>> overallDataMap = new ConcurrentHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterPropertiesSet() throws Exception {
        brokerDataExchangeService.subscribe(BrokerDataTopic.BIZ_REPORT_OVERALL, this);
    }

    @Override
    public void onDataEvent(BrokerDataTopic topic, Object payload) {
        if (topic == BrokerDataTopic.BIZ_REPORT_OVERALL) {
            for (WebSocketSession session : sessionMap.values()) {
                sessionSendMessage(session, WebSocketEvent.DATA_RESULT, payload);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionSendMessage(session, WebSocketEvent.SESSION_AUTH_REQUIRED, "");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessionMap.remove(session.getId());
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
                    performDataRequest(session, data.getPayload());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void performDataRequest(WebSocketSession session, Object payload) {
        SessionIdentity identity = identityMap.get(session.getId());

        if (sessionMap.get(session.getId()) != null && identity != null) {
            String orgId = identity.getOrgId();

            if (overallDataMap.get(orgId) == null || overallDataMap.get(orgId).isEmpty()) {
                Map<String, Integer> reportAllValue = bizReportRealtimeService
                        .getAllValues(identity.getOrgId());
                overallDataMap.put(orgId, reportAllValue);
            }

            sessionSendMessage(session, WebSocketEvent.DATA_RESULT, overallDataMap.get(orgId));
        }
    }

    private void performAuthClient(WebSocketSession session, Object payload) {
        if (payload instanceof String) {
            String sessionId = (String) payload;
            try {
                AuthSession authSession = authAdminService.authorizeWebPublicSession(sessionId);

                SessionIdentity identity = new SessionIdentity();
                identity.setOrgId(authSession.getOrgId());
                identity.setOrgCode(authSession.getOrgCode());

                sessionMap.put(session.getId(), session);
                identityMap.put(session.getId(), identity);
                sessionSendMessage(session, WebSocketEvent.SESSION_AUTH_RESULT, "SUCCESS");

            } catch (Exception ignored) {
                ignored.printStackTrace();
                sessionSendMessage(session, WebSocketEvent.SESSION_AUTH_RESULT, "FAILED");
                sessionClose(session);
            }
        }
    }

    private void sessionSendMessage(WebSocketSession session, WebSocketEvent event, Object payload) {
        try {
            session.sendMessage(composeMessage(event, payload));
        } catch (Exception ignored) {}
    }

    private void sessionClose(WebSocketSession session) {
        try {
            sessionMap.remove(session.getId());
            session.close();
        } catch (Exception ignored) {}
    }

    private TextMessage composeMessage(WebSocketEvent event, Object payload) {
        String strData = StringUtil.EMPTY;
        try {
            WebSocketData webSocketData = new WebSocketData();
            webSocketData.setEvent(event.getCode());
            webSocketData.setPayload(payload);

            strData = objectMapper.writeValueAsString(webSocketData);
        } catch (Exception ignored) {}

        return new TextMessage(strData);
    }
}