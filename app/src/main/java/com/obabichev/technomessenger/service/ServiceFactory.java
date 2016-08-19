package com.obabichev.technomessenger.service;

import com.obabichev.technomessenger.network.SocketProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by olegchuikin on 19/08/16.
 */

@Module
public class ServiceFactory {

    @Provides
    @Singleton
    RequestService createRequestService(SocketProvider socketProvider) {
        return new RequestServiceImpl(socketProvider);
    }

    @Provides
    @Singleton
    ResponseService createResponseService(SocketProvider socketProvider) {
        return new ResponseServiceImpl(socketProvider);
    }
}
