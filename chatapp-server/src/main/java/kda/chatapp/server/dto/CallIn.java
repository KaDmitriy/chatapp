package kda.chatapp.server.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Отправка вызова вызываемому (in)
 */
public class CallIn {

    private Integer callUserID;

    public CallIn() {
    }

    public CallIn(Integer callUserID) {
        this.callUserID = callUserID;
    }

    public Integer getCallUserID() {
        return callUserID;
    }

    public void setCallUserID(Integer callUserID) {
        this.callUserID = callUserID;
    }
}
