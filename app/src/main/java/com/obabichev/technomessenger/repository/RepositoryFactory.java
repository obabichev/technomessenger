package com.obabichev.technomessenger.repository;

import android.content.Context;

import com.obabichev.technomessenger.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by olegchuikin on 17/08/16.
 */

@Module
public class RepositoryFactory {

    Context context;

    public RepositoryFactory(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    UserRepository createUserRepository() {
        return new UserRepositoryImpl(context);
    }
}
