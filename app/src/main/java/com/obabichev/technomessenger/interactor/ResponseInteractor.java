package com.obabichev.technomessenger.interactor;

import com.obabichev.technomessenger.mapi.Response;

import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;


/**
 * Created by olegchuikin on 12/08/16.
 */

public interface ResponseInteractor {

    ConnectableObservable<Response> messagesObservable();

    <T> PublishSubject<T> getSubjectForResponses(Class<T> tClass);

}
