package com.obabichev.technomessenger.cleanmvp.interactor;

import rx.Observable;

public interface Interactor<T, R> {

    Observable<R> createObservable(T argument);
}
