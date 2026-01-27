package kda.chatapp.server.dto;

public class CallToCheck {

    private Integer userFromId;
    private Integer userToId;
    private Boolean check;

    public CallToCheck(Integer userFromId, Integer userToId, Boolean check) {
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.check = check;
    }

    public Integer getUserFromId() {
        return userFromId;
    }

    public Integer getUserToId() {
        return userToId;
    }

    public Boolean getCheck() {
        return check;
    }
}
