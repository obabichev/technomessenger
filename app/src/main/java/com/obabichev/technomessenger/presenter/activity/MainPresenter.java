package com.obabichev.technomessenger.presenter.activity;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.presenter.BasePresenter;
import com.obabichev.technomessenger.cleanmvp.presenter.activity.BaseActivityPresenter;
import com.obabichev.technomessenger.cleanmvp.view.activity.ActivityView;
import com.obabichev.technomessenger.interactor.SocketInteractorImpl;
import com.obabichev.technomessenger.model.Message;
import com.obabichev.technomessenger.model.WelcomeMessage;
import com.obabichev.technomessenger.view.activity.MainView;

import rx.Subscriber;

/**
 * Created by olegchuikin on 11/08/16.
 */

public class MainPresenter extends BaseActivityPresenter<MainView> {

    @Override
    public void onCreate() {
        super.onCreate();


        new SocketInteractorImpl().messagesObservable().subscribe(new Subscriber<Message>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Message message) {
                if (message instanceof WelcomeMessage){
                    view.switchToLoginScreen();
                }
                Log.d(App.SOCKET_TAG, message.toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (view.isActivityStart()) {
            view.switchToSplashScreen();
        }
    }
}
