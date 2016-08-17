package com.obabichev.technomessenger.interactor;

import com.obabichev.technomessenger.mapi.Response;

import rx.observables.ConnectableObservable;

/**
 * Created by olegchuikin on 12/08/16.
 */

public interface ResponseInteractor {

    ConnectableObservable<Response> messagesObservable();

}
