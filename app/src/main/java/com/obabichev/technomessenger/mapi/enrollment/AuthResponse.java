package com.obabichev.technomessenger.mapi.enrollment;

import com.obabichev.technomessenger.mapi.Response;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class AuthResponse extends Response {

    String sid;

    String cid;

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
