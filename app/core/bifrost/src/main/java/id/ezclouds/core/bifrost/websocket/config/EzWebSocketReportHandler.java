/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.common.model.websocket.WebSocketData;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzWebSocketReportHandler.java, v 0.1 2024‐08‐31 10:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzWebSocketReportHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println(session.getId() +" - connected!");
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println(session.getId() +" - closed!");
        sessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        System.out.println(message.getPayload());

        try {
            WebSocketData data = objectMapper.readValue(message.getPayload(), WebSocketData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}