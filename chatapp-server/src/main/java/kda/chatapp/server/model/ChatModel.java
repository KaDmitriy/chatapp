package kda.chatapp.server.model;

import java.util.UUID;

public class ChatModel {
    private User fromUSer;
    private User toUSer;
    private UUID uid;
    private String name;

    public User getFromUSer() {
        return fromUSer;
    }

    public void setFromUSer(User fromUSer) {
        this.fromUSer = fromUSer;
    }

    public User getToUSer() {
        return toUSer;
    }

    public void setToUSer(User toUSer) {
        this.toUSer = toUSer;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getName() {
        return fromUSer.getName()+" - "+ toUSer.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

}
