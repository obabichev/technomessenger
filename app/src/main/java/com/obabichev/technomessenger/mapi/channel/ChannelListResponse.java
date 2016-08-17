package com.obabichev.technomessenger.mapi.channel;

import com.obabichev.technomessenger.mapi.Response;
import com.obabichev.technomessenger.model.Channel;

import java.util.List;

/**
 * Created by olegchuikin on 17/08/16.
 */

public class ChannelListResponse extends Response {

    private int status;

    private String error;

    private List<Channel> channels;

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

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public String getAction() {
        return "channellist";
    }
}
