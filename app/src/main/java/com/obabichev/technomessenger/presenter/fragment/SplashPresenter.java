package com.obabichev.technomessenger.presenter.fragment;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.interactor.SocketInteractor;
import com.obabichev.technomessenger.model.Message;
import com.obabichev.technomessenger.model.WelcomeMessage;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.fragment.SplashView;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by olegchuikin on 11/08/16.
 */

public class SplashPresenter extends BaseFragmentPresenter<SplashView, MainView> {

    @Inject
    SocketInteractor socketInteractor;

    private Subscription serverSubscription;

    @Override
    public void onCreate() {
        super.onCreate();

        App.getComponent().inject(this);
    }


    @Override
    public void onResume() {
        super.onResume();

        serverSubscription = socketInteractor.messagesObservable().subscribe(new Subscriber<Message>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(App.SOCKET_TAG, e.getMessage(), e);
            }

            @Override
            public void onNext(Message message) {
                if (message != null && message instanceof WelcomeMessage) {
                    view.getActivityView().switchToLoginScreen();
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        serverSubscription.unsubscribe();
    }
}
