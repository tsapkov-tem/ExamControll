package com.example.examcontrol.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Service
public class WebSocketEventListener {
    private final SimpMessageSendingOperations sendingOperations;
    private static final Logger LOGGER = LoggerFactory.getLogger (WebSocketEventListener.class);

    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }

    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent event){}
}
