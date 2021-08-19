package com.test.cbback.config;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SimpleHandlerWS extends TextWebSocketHandler {

    protected Log logger = LogFactory.getLog(SimpleHandlerWS.class);


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        TextMessage message = new TextMessage("HOLA GUIDO");
        session.sendMessage(message);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        this.logger.info("Received: " + message + " ()");
        session.close();
        // this.latch.countDown();
    }

}