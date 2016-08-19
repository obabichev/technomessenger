package com.obabichev.technomessenger.mapi;

/**
 * Created by olegchuikin on 17/08/16.
 */

public abstract class Response extends DomainObject {

    private int status;

    private String error;


    public abstract String getAction();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
