package com.obabichev.technomessenger.model.enrollment;

import com.obabichev.technomessenger.model.Message;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class AuthResponse extends Message {

    int status;

    String error;

    String sid;

    String cid;

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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String getAction() {
        return "auth";
    }
}
