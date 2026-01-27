package kda.chatapp.client.ws;

import kda.chatapp.client.ConnectHttp;
import kda.chatapp.client.service.CallService;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WS {

    ConnectHttp connectHttp;
    CallService callService;

    public WS(ConnectHttp connectHttp, CallService callService) {
        this.connectHttp = connectHttp;
        this.callService = callService;
    }

    public void start(){
        //Base settings connection
        String urlStr = "ws://localhost:8080/gs-guide-websocket";
        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        handshakeHeaders.add("Cookie", String.format("JSESSIONID=%s", connectHttp.getSessionID()));

        //Init client
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        org.springframework.messaging.simp.stomp.StompSession sessionWS = null;
        try {
            CompletableFuture<StompSession> stompSessionCompletableFuture = stompClient.connectAsync(urlStr, handshakeHeaders,  new StompSessionHandlerAdapter() {});
            sessionWS = stompSessionCompletableFuture.get();

            SubChannelCallHandler subChannelCallHandler = new SubChannelCallHandler(connectHttp, callService);
            sessionWS.subscribe(subChannelCallHandler.getSubUrl(), subChannelCallHandler);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

}
