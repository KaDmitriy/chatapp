package kda.chatapp.client.ws;

import kda.chatapp.client.ConnectHttp;
import kda.chatapp.client.service.CallService;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class WS {

    ConnectHttp connectHttp;
    CallService callService;

    public WS(ConnectHttp connectHttp, CallService callService) {
        this.connectHttp = connectHttp;
        this.callService = callService;
    }

    public void start(){

        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        handshakeHeaders.add("Cookie", String.format("JSESSIONID=%s", connectHttp.getSessionID()));

        String urlStr = "ws://localhost:8080/gs-guide-websocket";
        SubChannelCallHandler subChannelCallHandler = new SubChannelCallHandler(connectHttp, callService);
        stompClient.connectAsync(urlStr, handshakeHeaders, subChannelCallHandler);

    }

}
