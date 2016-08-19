package com.obabichev.technomessenger.interactor;

import com.google.gson.internal.ObjectConstructor;
import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.mapi.ErrorCodeFromServerException;
import com.obabichev.technomessenger.mapi.enrollment.AuthRequest;
import com.obabichev.technomessenger.mapi.enrollment.AuthResponse;
import com.obabichev.technomessenger.mapi.enrollment.RegisterRequest;
import com.obabichev.technomessenger.mapi.enrollment.RegisterResponse;
import com.obabichev.technomessenger.model.LoginCredentionals;
import com.obabichev.technomessenger.model.RegisterCredentionals;
import com.obabichev.technomessenger.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

import static com.obabichev.technomessenger.mapi.ResponseCodes.ErrOK;

/**
 * Created by olegchuikin on 19/08/16.
 */
public class EnrollmentInteractorImpl implements EnrollmentInteractor {

    @Inject
    RequestInteractor requestInteractor;

    @Inject
    ResponseInteractor responseInteractor;

    @Inject
    UserRepository userRepository;

    public EnrollmentInteractorImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<Void> register(RegisterCredentionals credentionals) {

        /*Observable<Void> result = responseInteractor.getSubjectForResponses(RegisterResponse.class)
                .flatMap(new Func1<RegisterResponse, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(RegisterResponse registerResponse) {

                        return Observable.just(null);
                    }
                });*/

        Observable<Void> result = responseInteractor.getSubjectForResponses(AuthResponse.class)
                .flatMap(new Func1<AuthResponse, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(AuthResponse authResponse) {
                        userRepository.fixateUserCredentionals();
                        App.sid = authResponse.getSid();
                        return Observable.just(null);
                    }
                });

        RegisterRequest request = new RegisterRequest();
        request.setLogin(credentionals.getCid());
        request.setPass(credentionals.getPassword());
        request.setNick(credentionals.getNick());

        userRepository.setUserId(credentionals.getCid());
        userRepository.setUserPassword(credentionals.getPassword());

        requestInteractor.sendMessage(request);
        return result;
    }

    @Override
    public Observable<Void> login(LoginCredentionals credentionals) {

        Observable<Void> result = responseInteractor.getSubjectForResponses(AuthResponse.class)
                .flatMap(new Func1<AuthResponse, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(AuthResponse authResponse) {
                        if (authResponse.getStatus() != ErrOK) {
                            userRepository.clearUserCredentionals();
                            ErrorCodeFromServerException error = new ErrorCodeFromServerException(authResponse.getStatus(), authResponse.getError());
                            return Observable.error(error);
                        }
                        userRepository.fixateUserCredentionals();
                        App.sid = authResponse.getSid();
                        return Observable.just(null);
                    }
                });

        AuthRequest request = new AuthRequest();
        request.setLogin(credentionals.getCid());
        request.setPass(credentionals.getPassword());

        userRepository.setUserId(credentionals.getCid());
        userRepository.setUserPassword(credentionals.getPassword());

        requestInteractor.sendMessage(request);

        return result;
    }

    @Override
    public Observable<Void> login() {
        if (userRepository.getUserId() == null) {
            return null;
        }

        LoginCredentionals loginCredentionals = new LoginCredentionals();
        loginCredentionals.setCid(userRepository.getUserId());
        loginCredentionals.setPassword(userRepository.getUserPassword());
        return login(loginCredentionals);
    }

}
