package com.obabichev.technomessenger.presenter.fragment;

import android.util.Log;
import android.widget.Toast;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.interactor.RequestInteractor;
import com.obabichev.technomessenger.interactor.ResponseInteractor;
import com.obabichev.technomessenger.mapi.Response;
import com.obabichev.technomessenger.mapi.enrollment.AuthRequest;
import com.obabichev.technomessenger.mapi.enrollment.AuthResponse;
import com.obabichev.technomessenger.mapi.enrollment.RegisterRequest;
import com.obabichev.technomessenger.presenter.activity.OnBackPressedListener;
import com.obabichev.technomessenger.repository.UserRepository;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.fragment.ChatsListFragment;
import com.obabichev.technomessenger.view.fragment.LoginFragment;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class LoginPresenter extends BaseFragmentPresenter<LoginFragment, MainView> {

    @Inject
    RequestInteractor requestInteractor;

    @Inject
    ResponseInteractor responseInteractor;

    @Inject
    UserRepository userRepository;

    private Subscription serverSubscription;

    private final OnBackPressedListener onBackPressedListener = new OnBackPressedListener() {
        @Override
        public boolean onBackPressed() {
            if (!view.isLoginState()){
                view.switchToLoginState();
                return true;
            }
            return false;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        App.getComponent().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        startObservingViewEvents();
        processServerMessages();

        view.getActivityView().hideActionBar();

        getActivityView().setOnBackPressedListener(onBackPressedListener);

        userRepository.extractUserCredentionals();
        Log.d(App.SOCKET_TAG, "Saved user id:" + userRepository.getUserId());
        Log.d(App.SOCKET_TAG, "Saved user password:" + userRepository.getUserPassword());

    }

    @Override
    public void onPause() {
        super.onPause();

        getActivityView().unsetOnBackPressedListener();
    }

    private void startObservingViewEvents() {

        observeClicks(view.getLoginButtonClicks(), new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                AuthRequest authRequest = new AuthRequest();
                authRequest.setLogin(view.getLogin());
                authRequest.setPass(view.getPassword());

                userRepository.setUserPassword(view.getPassword());

                requestInteractor.sendMessage(authRequest);
            }
        });

        observeClicks(view.getRegisterButtonClicks(), new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                view.switchToCreateAccountState();
            }
        });

        observeClicks(view.getCreateAccountButtonClicks(), new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                RegisterRequest request = new RegisterRequest();
                request.setLogin(view.getLogin());
                request.setPass(view.getPassword());
                request.setNick(view.getNickname());

                userRepository.setUserPassword(view.getPassword());

                requestInteractor.sendMessage(request);
            }
        });
    }

    private void observeClicks(Observable<Void> clicksObservable, Action1<Void> onNextAction) {
        clicksObservable.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }

    private void processServerMessages(){
        Log.d(App.SOCKET_TAG, "Server subscription on LoginScreen");
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
                Log.d(App.SOCKET_TAG, "Received response in LoginScreen");
                if (response instanceof AuthResponse) {
                    AuthResponse authResponse = (AuthResponse) response;
                    if (authResponse.getStatus() != 0){
                        Toast.makeText(view.getActivity(), "Auth: " + authResponse.getError(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    userRepository.setUserId(authResponse.getCid());
                    userRepository.fixateUserCredentionals();
                    App.sid = authResponse.getSid();
                    view.switchToFragment(ChatsListFragment.class, null, true);
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
