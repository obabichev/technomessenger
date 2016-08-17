package com.obabichev.technomessenger.presenter.fragment;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.interactor.RequestInteractor;
import com.obabichev.technomessenger.interactor.ResponseInteractor;
import com.obabichev.technomessenger.mapi.Response;
import com.obabichev.technomessenger.mapi.WelcomeMessage;
import com.obabichev.technomessenger.mapi.enrollment.AuthRequest;
import com.obabichev.technomessenger.mapi.enrollment.AuthResponse;
import com.obabichev.technomessenger.repository.UserRepository;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.fragment.ChatsListFragment;
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

    @Inject
    RequestInteractor requestInteractor;

    @Inject
    UserRepository userRepository;

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
                    if (userRepository.getUserId() != null){
                        tryLogin();
                    } else {
                        view.getActivityView().switchToLoginScreen();
                    }
                }

                if (response != null && response instanceof AuthResponse){
                    App.sid = ((AuthResponse) response).getSid();
                    view.switchToFragment(ChatsListFragment.class, null, false);
                }
            }
        });
        responseInteractor.messagesObservable().connect();

        view.getActivityView().hideActionBar();
    }

    private void tryLogin(){
        AuthRequest request = new AuthRequest();
        request.setLogin(userRepository.getUserId());
        request.setPass(userRepository.getUserPassword());
        requestInteractor.sendMessage(request);
    }

    @Override
    public void onStop() {
        super.onStop();

        serverSubscription.unsubscribe();
    }
}
