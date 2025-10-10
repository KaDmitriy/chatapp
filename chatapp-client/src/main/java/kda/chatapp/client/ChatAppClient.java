package kda.chatapp.client;

import kda.chatapp.client.form.MainForm;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * https://www.baeldung.com/websockets-api-java-spring-client#bd-websocket-server
 */

public class ChatAppClient {

	public static void main(String[] args)  {

        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        String user = "user1", pass = "1";
        handshakeHeaders.add("Authorization", generateBasicAuthToken(user, pass));

        StompSessionHandler sessionHandler = new MyStompSessionHandler();

        String urlStr = "ws://localhost:8080/gs-guide-websocket";
        //URI url = new URI(urlStr);
        stompClient.connectAsync(urlStr, handshakeHeaders, sessionHandler);

        //new Scanner(System.in).nextLine(); // Don't close immediately.

        CallBack callBack = new CallBack();
        MainForm mainForm = new MainForm(callBack);
        mainForm.incoming("OK");
	}

    public static String generateBasicAuthToken(String username, String password) {
        // Concatenate username and password with a colon
        String credentials = username + ":" + password;

        // Encode the credentials in Base64
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        // Prefix with "Basic " to form the Authorization header value
        return "Basic " + encodedCredentials;
    }

}
