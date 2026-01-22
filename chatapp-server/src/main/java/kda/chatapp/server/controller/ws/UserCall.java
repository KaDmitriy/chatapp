package kda.chatapp.server.controller.ws;

import kda.chatapp.server.dto.CallIn;
import kda.chatapp.server.dto.CallOut;
import kda.chatapp.server.dto.CallOutCheck;
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
     * @param callOut
     * @return
     * @throws Exception
     */
    @MessageMapping("/call/check")
    @SendToUser(value = "/topic/call/ischeck", broadcast = false)
    public CallOutCheck greeting(CallOut callOut) throws Exception {
        log.info("call bell OutUserID:{} , InUserID:{}", callOut.getOutUserID(), callOut.getInUserID());
        //messagingTemplate.setUserDestinationPrefix("/user2");
        messagingTemplate.convertAndSendToUser(Integer.toString(callOut.getInUserID()), "/topic/call/in", new CallIn(callOut.getOutUserID()));
        //messagingTemplate.convertAndSend("/topic/to", new CallIn(callOut.getOutUserID()));
        return new CallOutCheck(1, 2 ,true);
    }

}
