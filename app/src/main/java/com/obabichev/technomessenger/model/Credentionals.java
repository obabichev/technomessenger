package com.obabichev.technomessenger.model;

/**
 * Created by olegchuikin on 19/08/16.
 */

public abstract class Credentionals {

    private String cid;

    private String password;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
