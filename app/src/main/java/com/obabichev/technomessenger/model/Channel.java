package com.obabichev.technomessenger.model;

/**
 * Created by olegchuikin on 17/08/16.
 */

public class Channel {

    private String chid;

    private String name;

    private String descr;

    private int online;

    public String getChid() {
        return chid;
    }

    public void setChid(String chid) {
        this.chid = chid;
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

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        if (online != channel.online) return false;
        if (chid != null ? !chid.equals(channel.chid) : channel.chid != null) return false;
        if (name != null ? !name.equals(channel.name) : channel.name != null) return false;
        return descr != null ? descr.equals(channel.descr) : channel.descr == null;

    }

    @Override
    public int hashCode() {
        int result = chid != null ? chid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + online;
        return result;
    }
}
