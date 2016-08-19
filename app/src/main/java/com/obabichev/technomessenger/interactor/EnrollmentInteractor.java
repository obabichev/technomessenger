package com.obabichev.technomessenger.interactor;

import com.obabichev.technomessenger.model.LoginCredentionals;
import com.obabichev.technomessenger.model.RegisterCredentionals;

import rx.Observable;

/**
 * Created by olegchuikin on 19/08/16.
 */

public interface EnrollmentInteractor {

    Observable<Void> register(RegisterCredentionals credentionals);

    Observable<Void> login(LoginCredentionals credentionals);

    Observable<Void> login();

}
