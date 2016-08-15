package com.obabichev.technomessenger.view.fragment;

import rx.Observable;

/**
 * Created by olegchuikin on 15/08/16.
 */

public interface LoginScreenView {

    Observable<Void> getLoginButtonClicks();

    Observable<Void> getRegisterButtonClicks();

    Observable<Void> getCreateAccountButtonClicks();


    void switchToCreateAccount();

}
