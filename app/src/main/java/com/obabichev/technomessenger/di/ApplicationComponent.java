package com.obabichev.technomessenger.di;

import com.obabichev.technomessenger.interactor.ChannelInteractor;
import com.obabichev.technomessenger.interactor.ChannelInteractorImpl;
import com.obabichev.technomessenger.interactor.EnrollmentInteractorImpl;
import com.obabichev.technomessenger.interactor.InteractorFactory;
import com.obabichev.technomessenger.interactor.RequestInteractor;
import com.obabichev.technomessenger.interactor.RequestInteractorImpl;
import com.obabichev.technomessenger.interactor.ResponseInteractorImpl;
import com.obabichev.technomessenger.network.SocketProviderFactory;
import com.obabichev.technomessenger.presenter.fragment.ChannelsListPresenter;
import com.obabichev.technomessenger.presenter.fragment.LoginPresenter;
import com.obabichev.technomessenger.presenter.fragment.SplashPresenter;
import com.obabichev.technomessenger.repository.RepositoryFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by olegchuikin on 11/08/16.
 */

@Singleton
@Component(modules = {SocketProviderFactory.class, InteractorFactory.class, RepositoryFactory.class})
public interface ApplicationComponent {

    void inject(ResponseInteractorImpl socketInteractor);

    void inject(RequestInteractorImpl requestInteractor);

    void inject(LoginPresenter loginPresenter);

    void inject(SplashPresenter splashPresenter);

    void inject(ChannelsListPresenter channelsListPresenter);

    void inject(ChannelInteractorImpl channelInteractor);

    void inject(EnrollmentInteractorImpl enrollmentInteractor);
}
