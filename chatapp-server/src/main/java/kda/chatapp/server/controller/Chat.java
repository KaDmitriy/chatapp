package kda.chatapp.server.controller;

import kda.chatapp.server.dto.Message;
import kda.chatapp.server.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class Chat {

    private final Logger log = LoggerFactory.getLogger(Chat.class);

    @MessageMapping("/send")
    @SendToUser("/topic/to")
    public Greeting greeting(Message message) throws Exception {
        log.info("hello Chat. Message text:{} chatUID:{}", message.getText(), message.getChatUID().toString());
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getText()) + "!");
    }
}
