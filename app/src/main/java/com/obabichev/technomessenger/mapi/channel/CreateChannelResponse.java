package com.obabichev.technomessenger.mapi.channel;

import com.obabichev.technomessenger.mapi.Response;

/**
 * Created by olegchuikin on 17/08/16.
 */

public class CreateChannelResponse extends Response {

    private String chid;

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
