package com.obabichev.technomessenger.presenter.fragment;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.interactor.ResponseInteractor;
import com.obabichev.technomessenger.mapi.Response;
import com.obabichev.technomessenger.mapi.WelcomeMessage;
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
    ResponseInteractor responseInteractor;

    private Subscription serverSubscription;

    @Override
    public void onCreate() {
        super.onCreate();

        App.getComponent().inject(this);
    }


    @Override
    public void onResume() {
        super.onResume();

        Log.d(App.SOCKET_TAG, "Splash screen subscribe");
        serverSubscription = responseInteractor.messagesObservable().subscribe(new Subscriber<Response>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(App.SOCKET_TAG, e.getMessage(), e);
            }

            @Override
            public void onNext(Response response) {
                if (response != null && response instanceof WelcomeMessage) {
                    view.getActivityView().switchToLoginScreen();
                }
            }
        });
        responseInteractor.messagesObservable().connect();
    }

    @Override
    public void onStop() {
        super.onStop();

        serverSubscription.unsubscribe();
    }
}
