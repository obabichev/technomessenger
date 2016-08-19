package com.obabichev.technomessenger.interactor;

import com.obabichev.technomessenger.network.SocketProvider;
import com.obabichev.technomessenger.service.RequestService;
import com.obabichev.technomessenger.service.RequestServiceImpl;
import com.obabichev.technomessenger.service.ResponseService;
import com.obabichev.technomessenger.service.ResponseServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by olegchuikin on 15/08/16.
 */

@Module
public class InteractorFactory {

    @Provides
    @Singleton
    ChannelInteractor createChannelInteractor() {
        return new ChannelInteractorImpl();
    }

    @Provides
    @Singleton
    EnrollmentInteractor createEnrollmentInteractor() {
        return new EnrollmentInteractorImpl();
    }
}
