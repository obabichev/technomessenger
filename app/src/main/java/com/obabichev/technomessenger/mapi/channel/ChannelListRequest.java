package com.obabichev.technomessenger.mapi.channel;

import com.obabichev.technomessenger.mapi.Request;

/**
 * Created by olegchuikin on 17/08/16.
 */

public class ChannelListRequest extends Request {

    private String cid;

    private String sid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String getAction() {
        return "channellist";
    }
}
