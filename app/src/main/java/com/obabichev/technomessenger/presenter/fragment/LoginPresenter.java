package com.obabichev.technomessenger.presenter.fragment;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.interactor.EnrollmentInteractor;
import com.obabichev.technomessenger.model.LoginCredentionals;
import com.obabichev.technomessenger.model.RegisterCredentionals;
import com.obabichev.technomessenger.presenter.activity.OnBackPressedListener;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.fragment.ChannelsListFragment;
import com.obabichev.technomessenger.view.fragment.LoginFragment;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class LoginPresenter extends BaseFragmentPresenter<LoginFragment, MainView> {

    @Inject
    EnrollmentInteractor enrollmentInteractor;

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

        view.getActivityView().hideActionBar();

        getActivityView().setOnBackPressedListener(onBackPressedListener);

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

                LoginCredentionals credentionals = new LoginCredentionals();
                credentionals.setCid(view.getLogin());
                credentionals.setPassword(view.getPassword());

                enrollmentInteractor.login(credentionals).subscribe(
                        new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                view.switchToFragment(ChannelsListFragment.class, null, false);
                            }
                        }
                );
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

                RegisterCredentionals credentionals = new RegisterCredentionals();
                credentionals.setCid(view.getLogin());
                credentionals.setPassword(view.getPassword());
                credentionals.setNick(view.getNickname());

                enrollmentInteractor.register(credentionals).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        view.switchToFragment(ChannelsListFragment.class, null, false);
                    }
                });
            }
        });
    }

    private void observeClicks(Observable<Void> clicksObservable, Action1<Void> onNextAction) {
        clicksObservable.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
