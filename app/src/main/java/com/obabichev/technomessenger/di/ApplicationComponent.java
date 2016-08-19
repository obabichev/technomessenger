package com.obabichev.technomessenger.di;

import com.obabichev.technomessenger.interactor.ChannelInteractorImpl;
import com.obabichev.technomessenger.interactor.EnrollmentInteractorImpl;
import com.obabichev.technomessenger.interactor.InteractorFactory;
import com.obabichev.technomessenger.service.RequestServiceImpl;
import com.obabichev.technomessenger.service.ResponseServiceImpl;
import com.obabichev.technomessenger.network.SocketProviderFactory;
import com.obabichev.technomessenger.presenter.fragment.ChannelsListPresenter;
import com.obabichev.technomessenger.presenter.fragment.LoginPresenter;
import com.obabichev.technomessenger.presenter.fragment.SplashPresenter;
import com.obabichev.technomessenger.repository.RepositoryFactory;
import com.obabichev.technomessenger.service.ServiceFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by olegchuikin on 11/08/16.
 */

@Singleton
@Component(modules = {
        SocketProviderFactory.class,
        InteractorFactory.class,
        RepositoryFactory.class,
        ServiceFactory.class
})
public interface ApplicationComponent {

    void inject(ResponseServiceImpl socketInteractor);

    void inject(RequestServiceImpl requestInteractor);

    void inject(LoginPresenter loginPresenter);

    void inject(SplashPresenter splashPresenter);

    void inject(ChannelsListPresenter channelsListPresenter);

    void inject(ChannelInteractorImpl channelInteractor);

    void inject(EnrollmentInteractorImpl enrollmentInteractor);
}
