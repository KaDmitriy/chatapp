package kda.chatapp.server.dto;

/**
 *
 */
public class CallFrom {

    private Integer userFromId;
    private Integer userToId;

    public CallFrom(Integer userFromId, Integer userToId) {
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
