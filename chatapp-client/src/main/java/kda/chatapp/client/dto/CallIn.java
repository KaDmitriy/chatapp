package kda.chatapp.client.dto;

/**
 * Вызывающий
 * Кто (out) вызывает кого (in)
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