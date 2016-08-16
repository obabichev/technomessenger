package com.obabichev.technomessenger.di;

import com.obabichev.technomessenger.interactor.InteractorFactory;
import com.obabichev.technomessenger.interactor.RequestInteractor;
import com.obabichev.technomessenger.interactor.SocketInteractorImpl;
import com.obabichev.technomessenger.network.SocketProviderFactory;
import com.obabichev.technomessenger.presenter.fragment.LoginPresenter;
import com.obabichev.technomessenger.presenter.fragment.SplashPresenter;
import com.obabichev.technomessenger.view.fragment.SplashFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by olegchuikin on 11/08/16.
 */

@Singleton
@Component(modules = {SocketProviderFactory.class, InteractorFactory.class})
public interface ApplicationComponent {

    void inject(SocketInteractorImpl socketInteractor);

    void inject(RequestInteractor requestInteractor);

    void inject(LoginPresenter loginPresenter);

    void inject(SplashPresenter splashPresenter);
}
