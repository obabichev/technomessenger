package com.obabichev.technomessenger.view.fragment;

import com.obabichev.technomessenger.cleanmvp.view.fragment.FragmentView;
import com.obabichev.technomessenger.view.activity.MainView;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by olegchuikin on 15/08/16.
 */

public interface LoginView extends FragmentView<MainView> {

    Observable<Void> getLoginButtonClicks();

    Observable<Void> getRegisterButtonClicks();

    Observable<Void> getCreateAccountButtonClicks();

    PublishSubject<Void> backPressEvents();


    void switchToCreateAccountState();

    void switchToLoginState();

    String getLogin();

    String getPassword();

    String getNickname();

    boolean isLoginState();

}
