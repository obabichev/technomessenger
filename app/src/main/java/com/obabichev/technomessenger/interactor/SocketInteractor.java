package com.obabichev.technomessenger.interactor;

import com.obabichev.technomessenger.model.Message;

import rx.Observable;

/**
 * Created by olegchuikin on 12/08/16.
 */

public interface SocketInteractor {

    Observable<Message> messagesObservable();

}
