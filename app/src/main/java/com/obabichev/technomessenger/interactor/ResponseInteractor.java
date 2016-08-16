package com.obabichev.technomessenger.interactor;

import com.obabichev.technomessenger.model.Message;

import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * Created by olegchuikin on 12/08/16.
 */

public interface ResponseInteractor {

    ConnectableObservable<Message> messagesObservable();

}
