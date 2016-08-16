package com.obabichev.technomessenger.presenter.fragment;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.interactor.RequestInteractor;
import com.obabichev.technomessenger.model.enrollment.AuthRequest;
import com.obabichev.technomessenger.model.enrollment.RegisterRequest;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.fragment.LoginFragment;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class LoginPresenter extends BaseFragmentPresenter<LoginFragment, MainView> {

    @Inject
    RequestInteractor requestInteractor;

    @Override
    public void onCreate() {
        super.onCreate();
        App.getComponent().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        startObservingViewEvents();
    }

    private void startObservingViewEvents() {

        observeClicks(view.getLoginButtonClicks(), new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                AuthRequest authRequest = new AuthRequest();
                authRequest.setLogin(view.getLogin());
                authRequest.setPass(view.getPassword());

                requestInteractor.sendMessage(authRequest);
            }
        });

        observeClicks(view.getRegisterButtonClicks(), new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                view.switchToCreateAccount();
            }
        });

        observeClicks(view.getCreateAccountButtonClicks(), new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                RegisterRequest request = new RegisterRequest();
                request.setLogin(view.getLogin());
                request.setPass(view.getPassword());
                request.setNick(view.getNickname());

                requestInteractor.sendMessage(request);
            }
        });
    }

    private void observeClicks(Observable<Void> clicksObservable, Action1<Void> onNextAction) {
        clicksObservable.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }


}
