package kda.chatapp.server.dto;

import java.util.UUID;

public class Message {
    private UUID chatUID;
    private String text;

    public Message() {
    }

    public Message(UUID chatUID, String text) {
        this.chatUID = chatUID;
        this.text = text;
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
}
