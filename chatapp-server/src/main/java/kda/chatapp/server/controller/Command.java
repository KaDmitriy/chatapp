package kda.chatapp.server.controller;

import kda.chatapp.server.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class Command {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/api/test")
    public String test(){
        Greeting greeting = new Greeting("test command");
        messagingTemplate.convertAndSendToUser( "1", "/topic/to", greeting);
        //messagingTemplate.convertAndSendToUser( "1", "/topic/message", greeting);

        return "OK";
    }

}
