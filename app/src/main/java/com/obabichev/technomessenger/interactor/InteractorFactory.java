package com.obabichev.technomessenger.interactor;

import com.obabichev.technomessenger.network.SocketProvider;

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
    public ChannelInteractor createChannelInteractor(){
        return new ChannelInteractorImpl();
    }

    @Provides
    @Singleton
    public RequestInteractor createRequestInteractor(SocketProvider socketProvider){
        return new RequestInteractorImpl(socketProvider);
    }

    @Provides
    @Singleton
    ResponseInteractor createResponseInteractor(SocketProvider socketProvider){
        return new ResponseInteractorImpl(socketProvider);
    }

    @Provides
    @Singleton
    EnrollmentInteractor createEnrollmentInteractor(){
        return new EnrollmentInteractorImpl();
    }
}
