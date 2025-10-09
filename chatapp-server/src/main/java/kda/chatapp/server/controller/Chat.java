package kda.chatapp.server.controller;

import kda.chatapp.server.dto.Message;
import kda.chatapp.server.model.Greeting;
import kda.chatapp.server.model.User;
import kda.chatapp.server.service.UserServise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class Chat {

    private final Logger log = LoggerFactory.getLogger(Chat.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserServise userServise;

    @MessageMapping("/send")
    @SendToUser(destinations="/topic/to", broadcast=true)
    //@SendTo("/topic/to")
    public Greeting sendMessage(Message message, Principal principal) throws Exception {
        User curentUser = userServise.findByName(principal.getName());
        log.info("hello Chat. Message userID:{} text:{} chatUID:{}", message.getToIDUser(), message.getText(), message.getChatUID().toString());
        Thread.sleep(200); // simulated delay
        Greeting greeting = new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getText()) + "!");
        messagingTemplate.convertAndSendToUser( Integer.toString(curentUser.getId()), "/topic/to", greeting);
        messagingTemplate.convertAndSendToUser(message.getToIDUser(), "/topic/message", greeting);
        return greeting;
    }
}
