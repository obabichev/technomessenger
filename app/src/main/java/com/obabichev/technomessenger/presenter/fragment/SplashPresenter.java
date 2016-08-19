package com.obabichev.technomessenger.presenter.fragment;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.interactor.EnrollmentInteractor;
import com.obabichev.technomessenger.interactor.RequestInteractor;
import com.obabichev.technomessenger.interactor.ResponseInteractor;
import com.obabichev.technomessenger.mapi.WelcomeMessage;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.fragment.ChannelsListFragment;
import com.obabichev.technomessenger.view.fragment.SplashView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by olegchuikin on 11/08/16.
 */

public class SplashPresenter extends BaseFragmentPresenter<SplashView, MainView> {

    @Inject
    ResponseInteractor responseInteractor;

    @Inject
    RequestInteractor requestInteractor;

    @Inject
    EnrollmentInteractor enrollmentInteractor;

    private Subscription welcomeSubscription;

    @Override
    public void onCreate() {
        super.onCreate();
        App.getComponent().inject(this);
    }


    @Override
    public void onResume() {
        super.onResume();


        welcomeSubscription = responseInteractor.getSubjectForResponses(WelcomeMessage.class)
                .subscribe(new Action1<WelcomeMessage>() {
                    @Override
                    public void call(WelcomeMessage welcomeMessage) {

                        Log.d(App.FILTER_TAG, "Welcome message received in splash screen");

                        Observable<Void> login = enrollmentInteractor.login();
                        if (login == null) {
                            view.getActivityView().switchToLoginScreen();
                            return;
                        }

                        login.subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                Log.d(App.FILTER_TAG, "Login from saved credentionals");
                                view.switchToFragment(ChannelsListFragment.class, null, false);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                view.getActivityView().switchToLoginScreen();
                            }
                        });
                    }
                });

        responseInteractor.messagesObservable().connect();

        view.getActivityView().hideActionBar();
    }

    @Override
    public void onStop() {
        super.onStop();

        welcomeSubscription.unsubscribe();
    }
}
