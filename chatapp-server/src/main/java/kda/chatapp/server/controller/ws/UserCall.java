package kda.chatapp.server.controller.ws;

import kda.chatapp.server.dto.CallOut;
import kda.chatapp.server.dto.CallOutCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserCall {

    private final Logger log = LoggerFactory.getLogger(UserCall.class);

    /**
     * Процедура вызова
     * @param callOut
     * @return
     * @throws Exception
     */
    @MessageMapping("/call/check")
    public CallOutCheck greeting(CallOut callOut) throws Exception {
        log.info("call bell OutUserID:{} , InUserID:{}", callOut.getOutUserID(), callOut.getInUserID());
        return new CallOutCheck();
    }

}
