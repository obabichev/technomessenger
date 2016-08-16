package com.obabichev.technomessenger.view.fragment;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by olegchuikin on 15/08/16.
 */

public interface LoginView {

    Observable<Void> getLoginButtonClicks();

    Observable<Void> getRegisterButtonClicks();

    Observable<Void> getCreateAccountButtonClicks();

    PublishSubject<Void> backPressEvents();


    void switchToCreateAccount();

    String getLogin();

    String getPassword();

    String getNickname();

}
