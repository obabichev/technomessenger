package com.obabichev.technomessenger.interactor;


import com.obabichev.technomessenger.model.Channel;

import java.util.List;

import rx.Observable;

/**
 * Created by olegchuikin on 18/08/16.
 */

public interface ChannelInteractor {

    Observable<List<Channel>> getChannels();

    Observable<Channel> createChannel(String name, String description);

}
