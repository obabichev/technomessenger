package com.obabichev.technomessenger.network;

import dagger.Module;
import dagger.Provides;

/**
 * Created by olegchuikin on 15/08/16.
 */

@Module
public class SocketProviderFactory {

    @Provides
    public static SocketProvider createSocketProvider(){
        return new SocketProviderImpl();
    }

}
