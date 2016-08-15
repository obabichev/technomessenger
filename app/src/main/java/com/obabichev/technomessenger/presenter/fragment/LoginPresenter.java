package com.obabichev.technomessenger.presenter.fragment;

import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.fragment.LoginScreenFragment;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class LoginPresenter extends BaseFragmentPresenter<LoginScreenFragment, MainView> {

    @Override
    public void onResume() {
        super.onResume();

        startObservingViewEvents();
    }

    private void startObservingViewEvents() {

        observeClicks(view.getLoginButtonClicks(),
                new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //todo send login request
                    }
                });

        observeClicks(view.getRegisterButtonClicks(), new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                view.switchToCreateAccount();
            }
        });
    }

    private void observeClicks(Observable<Void> clicksObservable, Action1<Void> onNextAction) {
        clicksObservable.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }
}
