package kda.chatapp.server.dto;

/**
 * Подтверждение вызоызова
 * вызываемый (in) подтвердил или нет вызов
 */
public class CallOutCheck {

    private Integer outUserId;
    private Integer inUserId;
    private Boolean check;

    public CallOutCheck() {
    }

    public CallOutCheck(Integer outUserId, Integer inUserId, Boolean check) {
        this.outUserId = outUserId;
        this.inUserId = inUserId;
        this.check = check;
    }

    public Integer getOutUserId() {
        return outUserId;
    }

    public Integer getInUserId() {
        return inUserId;
    }

    public Boolean getCheck() {
        return check;
    }
}
