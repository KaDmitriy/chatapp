package kda.chatapp.client.ws;

import kda.chatapp.client.ConnectHttp;
import kda.chatapp.client.dto.CallTo;
import kda.chatapp.client.service.CallService;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class SubChannelCallHandler  implements StompSessionHandler {

    private ConnectHttp connectHttp;
    private CallService callService;
    private String subUrl;


    public SubChannelCallHandler(ConnectHttp connectHttp, CallService callService) {
        this.connectHttp = connectHttp;
        this.callService = callService;
        subUrl = "/user/"+connectHttp.getUserInfo().getId()+"/call/to";
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe(subUrl, this);
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
        return CallTo.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        //String msg = new String((byte[]) payload, StandardCharsets.UTF_8);
        CallTo callTo = (CallTo) payload;
        callService.callIn(callTo);
        System.out.println("handleFrame UserFromId:"+callTo.getUserFromId());
    }

    public String getSubUrl() {
        return subUrl;
    }
}
