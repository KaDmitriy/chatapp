package kda.chatapp.server.dto;

import java.util.UUID;


/**
 * Вызывающий
 * Кто (out) вызывает кого (in)
 */
public class CallOut {

    private Integer outUserID;
    private Integer inUserID;

    public CallOut() {
    }

    public CallOut(Integer outUserID, Integer inUserID) {
        this.outUserID = outUserID;
        this.inUserID = inUserID;
    }

    public Integer getOutUserID() {
        return outUserID;
    }

    public void setOutUserID(Integer outUserID) {
        this.outUserID = outUserID;
    }

    public Integer getInUserID() {
        return inUserID;
    }

    public void setInUserID(Integer inUserID) {
        this.inUserID = inUserID;
    }
}
