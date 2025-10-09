package kda.chatapp.server.dto;

import java.util.UUID;

public class Message {
    private UUID chatUID;
    private String text;
    private String toIDUser;

    public Message() {
    }

    public Message(UUID chatUID, String toIDUser, String text) {
        this.chatUID = chatUID;
        this.text = text;
        this.toIDUser = toIDUser;
    }

    public UUID getChatUID() {
        return chatUID;
    }

    public void setChatUID(UUID chatUID) {
        this.chatUID = chatUID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToIDUser() {
        return toIDUser;
    }

    public void setToIDUser(String toIDUser) {
        this.toIDUser = toIDUser;
    }
}
