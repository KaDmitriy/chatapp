package kda.chatapp.server.controller;

import kda.chatapp.server.model.Greeting;
import kda.chatapp.server.model.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.UUID;

@Controller
public class Chat {

    private final Logger log = LoggerFactory.getLogger(Chat.class);

    @MessageMapping("/chat/{uid}")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message, @DestinationVariable UUID uid) throws Exception {
        log.info("hello");
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
