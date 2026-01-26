package kda.chatapp.client;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class ConnectHttp {

    String username;
    String password;

    private Integer statusCode;
    private String sessionID;


    public void connect(String userName, String password) {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/login"))
                                    .header("Content-Type", "application/x-www-form-urlencoded")
                                    .POST(HttpRequest.BodyPublishers.ofString( String.format("username=%s&password=%s", userName, password) ))
                                    .build();

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            statusCode = response.statusCode();
            System.out.println("statusCode: "+statusCode);

            HttpHeaders headers = response.headers();
            Optional<String> cookie = headers.firstValue("set-cookie");
            sessionID = parseCookie( cookie.get() );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public String generateBasicAuthToken(String username, String password) {
        // Concatenate username and password with a colon
        String credentials = username + ":" + password;

        // Encode the credentials in Base64
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        // Prefix with "Basic " to form the Authorization header value
        return "Basic " + encodedCredentials;
    }

    protected String parseCookie(String value) {
        return Arrays.stream( value.split(";") )
                .findFirst()
                .get()
                .split("=")[1];
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getSessionID() {
        return sessionID;
    }
}
