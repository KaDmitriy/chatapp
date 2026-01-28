package kda.chatapp.client;

import kda.chatapp.client.form.MainForm;
import kda.chatapp.client.service.CallService;
import kda.chatapp.client.ws.WS;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * https://www.baeldung.com/websockets-api-java-spring-client#bd-websocket-server
 */

public class ChatAppClient {

	public static void main(String[] args)  {
        CallService callService = new CallService();
        CallBack callBack = new CallBack();
        ConnectHttp connectHttp = new ConnectHttp();
        WS ws = new WS(connectHttp, callService);
        MainForm mainForm = new MainForm(callBack, connectHttp, ws, callService);
        mainForm.incoming("OK");
	}

}
