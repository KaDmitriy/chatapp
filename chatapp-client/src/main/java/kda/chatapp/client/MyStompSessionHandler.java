package kda.chatapp.client;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

public class MyStompSessionHandler implements StompSessionHandler {


    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/user/1/topic/message", this);
        //session.send("/app/chat", getSampleMessage());
        System.out.println("afterConnected");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.out.println("handleException");
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.out.println("handleTransportError");
        System.out.println(exception.getMessage());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        System.out.println("getPayloadType");
        return null;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("handleFrame");
    }
}
