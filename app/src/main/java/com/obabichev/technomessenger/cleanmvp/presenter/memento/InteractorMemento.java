package com.obabichev.technomessenger.cleanmvp.presenter.memento;

import com.obabichev.technomessenger.cleanmvp.presenter.BasePresenter;
import com.obabichev.technomessenger.cleanmvp.presenter.SubscriberFactory;

import rx.Observable;

public interface InteractorMemento<P extends BasePresenter<?, ?>, R> {

    void store(Observable<R> interactorObservable, SubscriberFactory<P, R> factory);

    boolean isNotEmpty();

    Observable<R> getInteractorObservable();

    SubscriberFactory<P, R> getSubscriberFactory();
}