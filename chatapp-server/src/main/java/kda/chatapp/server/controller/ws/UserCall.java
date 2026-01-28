package kda.chatapp.server.controller.ws;

import kda.chatapp.server.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class UserCall {

    private final Logger log = LoggerFactory.getLogger(UserCall.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Процедура вызова
     * @param
     * @return
     * @throws Exception
     */
    @MessageMapping("/call/from")
    public void callFrom(CallFrom callFrom) throws Exception {
        log.info("call bell fromUserID:{} , toUserID:{}", callFrom.getUserFromId(), callFrom.getUserToId());
        //messagingTemplate.setUserDestinationPrefix("/user2");
        messagingTemplate.convertAndSendToUser(Integer.toString(callFrom.getUserToId()), "/call/to", new CallTo(callFrom.getUserFromId(), callFrom.getUserToId()));
        //messagingTemplate.convertAndSend("/topic/to", new CallIn(callOut.getOutUserID()));
    }

    @MessageMapping("/call/tocheck")
    public void callToCheck(CallToCheck callToCheck) throws Exception {
        log.info("CallToCheck > getUserFromId:{}, getUserToId:{}, getCheck:{}", callToCheck.getUserFromId(), callToCheck.getUserToId(), callToCheck.getCheck());
        messagingTemplate.convertAndSendToUser(Integer.toString(callToCheck.getUserFromId()), "/call/confirm", callToCheck);
        //messagingTemplate.setUserDestinationPrefix("/user2");
        //messagingTemplate.convertAndSendToUser(Integer.toString(callOut.getInUserID()), "/topic/call/confirm", new CallIn(callOut.getOutUserID()));
        //messagingTemplate.convertAndSend("/topic/to", new CallIn(callOut.getOutUserID()));
    }

}
