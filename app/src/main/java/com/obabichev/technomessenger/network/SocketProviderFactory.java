package com.obabichev.technomessenger.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by olegchuikin on 15/08/16.
 */

@Module
public class SocketProviderFactory {

    @Provides
    @Singleton
    public SocketProvider createSocketProvider(){
        return new SocketProviderImpl();
    }

}
