package com.obabichev.technomessenger.di;

import com.obabichev.technomessenger.interactor.SocketInteractorImpl;
import com.obabichev.technomessenger.network.SocketProviderFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by olegchuikin on 11/08/16.
 */

@Singleton
@Component(modules = {SocketProviderFactory.class})
public interface ApplicationComponent {

    void inject(SocketInteractorImpl socketInteractor);

}
