package com.obabichev.technomessenger.mapi.channel;

import com.obabichev.technomessenger.mapi.Response;

/**
 * Created by olegchuikin on 17/08/16.
 */

public class CreateChannelResponse extends Response {

    private int status;

    private String error;

    private String chid;

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

    public String getChid() {
        return chid;
    }

    public void setChid(String chid) {
        this.chid = chid;
    }

    @Override
    public String getAction() {
        return "createchannel";
    }
}
