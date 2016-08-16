package com.obabichev.technomessenger;

import android.app.Application;

import com.obabichev.technomessenger.di.ApplicationComponent;
import com.obabichev.technomessenger.di.DaggerApplicationComponent;
import com.obabichev.technomessenger.interactor.InteractorFactory;
import com.obabichev.technomessenger.network.SocketProviderFactory;

/**
 * Created by olegchuikin on 11/08/16.
 */

public class App extends Application {

    public static final String SOCKET_TAG = "SOCKET_CONNECTION";


    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = buildComponent();
    }

    private ApplicationComponent buildComponent() {
        return DaggerApplicationComponent.builder()
                .interactorFactory(new InteractorFactory())
                .socketProviderFactory(new SocketProviderFactory())
                .build();
    }

}
