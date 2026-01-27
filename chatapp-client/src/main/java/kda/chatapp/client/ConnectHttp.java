package kda.chatapp.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kda.chatapp.client.dto.UserInfo;

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
    private String httpSessionID;
    private UserInfo userInfo;

    private Boolean isConnected = false;

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
            String location = headers.allValues("Location").get(0);
            if(location.indexOf("error")>0){
                isConnected = false;
                return;
            }
            Optional<String> cookie = headers.firstValue("set-cookie");
            httpSessionID = parseCookie( cookie.get() );
            this.username = userName;
            isConnected = true;
            userInfo = getUserInfoConnect(client);
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

    public UserInfo getUserInfoConnect(HttpClient client) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/user/info"))
                .header("Cookie", String.format("JSESSIONID=%s", httpSessionID))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(response.body(), UserInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userInfo;
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
        return httpSessionID;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
