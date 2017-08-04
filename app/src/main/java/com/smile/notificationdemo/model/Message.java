package com.smile.notificationdemo.model;

/**
 * @author Smile Wei
 * @since 2017/8/4.
 */

public class Message {

    private String text;

    private long time;

    private String sender;

    public Message(String text, long time, String sender) {
        this.text = text;
        this.time = time;
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
