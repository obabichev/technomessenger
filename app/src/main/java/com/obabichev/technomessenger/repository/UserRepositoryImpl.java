package com.obabichev.technomessenger.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.obabichev.technomessenger.App;

/**
 * Created by olegchuikin on 17/08/16.
 */
public class UserRepositoryImpl implements UserRepository {

    private final String USER_PREFERENCES = "USER_PREFERENCES";

    private final String USER_ID = "USER_ID";
    private final String USER_PASSWORD = "USER_PASSWORD";

    private SharedPreferences preferences;

    private String userId;

    private String userPassword;

    public UserRepositoryImpl(Context context) {
        preferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
        extractUserCredentionals();
    }

    @Override
    public void fixateUserCredentionals() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_ID, userId);
        editor.putString(USER_PASSWORD, userPassword);
        editor.apply();
    }

    @Override
    public void extractUserCredentionals() {
            userId = preferences.getString(USER_ID, null);
            userPassword = preferences.getString(USER_PASSWORD, null);
    }

    @Override
    public void clearUserCredentionals() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(USER_ID);
        editor.remove(USER_PASSWORD);
        editor.apply();
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
