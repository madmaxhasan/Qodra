package com.example.qodra;

public class ItemChat {

    private String msgText;
    private String msgUser;

    public ItemChat(String msgText, String msgUser) {
        this.msgText = msgText;
        this.msgUser = msgUser;
    }

    public String getMsgText() {
        return msgText;
    }

    public String getMsgUser() {
        return msgUser;
    }
}
