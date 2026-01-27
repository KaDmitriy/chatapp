package kda.chatapp.client.dto;

public class CallTo {

    private Integer userFromId;
    private Integer userToId;

    public CallTo() {
    }

    public CallTo(Integer userFromId, Integer userToId) {
        this.userFromId = userFromId;
        this.userToId = userToId;
    }

    public Integer getUserFromId() {
        return userFromId;
    }

    public Integer getUserToId() {
        return userToId;
    }
}
