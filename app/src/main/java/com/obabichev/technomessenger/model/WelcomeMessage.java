package com.obabichev.technomessenger.model;

/**
 * Created by olegchuikin on 12/08/16.
 */

public class WelcomeMessage extends Message{

    private String action;

    private String message;

    private long time;

    public WelcomeMessage() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
