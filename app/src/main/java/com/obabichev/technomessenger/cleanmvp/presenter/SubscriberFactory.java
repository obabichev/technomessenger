package com.obabichev.technomessenger.cleanmvp.presenter;

import rx.Subscriber;

public interface SubscriberFactory<P extends BasePresenter<?, ?>, R> {
    Subscriber<R> create(P presenter);
}
