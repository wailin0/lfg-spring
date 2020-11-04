package com.lfg.spring.model.enums;

public enum WSEvent {
    CHAT,
    NOTIFICATION;

    public String Chat(){

        return CHAT.toString();
    }

    public String Notification(){

        return NOTIFICATION.toString();
    }
}