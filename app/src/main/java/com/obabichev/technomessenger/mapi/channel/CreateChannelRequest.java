package com.obabichev.technomessenger.mapi.channel;

import com.obabichev.technomessenger.mapi.Request;

/**
 * Created by olegchuikin on 17/08/16.
 */

public class CreateChannelRequest extends Request {

    private String cid;

    private String sid;

    private String name;

    private String descr;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String getAction() {
        return "createchannel";
    }
}
