package com.example.nullclassapp.model;

public class Message {
    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT = "bot";

    String message;
    String sentBy ;
    String linkVal;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getLinkVal() {
        return linkVal;
    }

    public void setLinkVal(String linkVal) {
        this.linkVal = linkVal;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", linkVal='" + linkVal + '\'' +
                '}';
    }

    public Message(String message, String sentBy, String linkVal) {
        this.message = message;
        this.sentBy = sentBy;
        this.linkVal = linkVal;
    }
}
