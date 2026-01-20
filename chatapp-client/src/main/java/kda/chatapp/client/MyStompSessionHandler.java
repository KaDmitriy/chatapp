package kda.chatapp.client;

import kda.chatapp.client.dto.Greeting;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.beans.Encoder;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class MyStompSessionHandler implements StompSessionHandler {


    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/user/1/topic/message", this);
        //session.send("/app/chat", getSampleMessage());
        System.out.println("afterConnected");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        String msg = new String(payload, StandardCharsets.UTF_8);
        System.out.println("handleException msg:"+msg);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.out.println("handleTransportError");
        System.out.println(exception.getMessage());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        System.out.println("getPayloadType");
        //return Object.class;
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {

        //String msg = new String((byte[]) payload, StandardCharsets.UTF_8);
        Greeting greeting = (Greeting) payload;
        System.out.println("handleFrame msg:"+greeting.getContent());
    }
}
