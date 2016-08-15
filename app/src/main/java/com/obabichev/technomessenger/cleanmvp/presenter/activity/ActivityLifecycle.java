package com.obabichev.technomessenger.cleanmvp.presenter.activity;

import com.obabichev.technomessenger.cleanmvp.presenter.Lifecycle;

public interface ActivityLifecycle extends Lifecycle {

    void onCreate();

    void onStart();

    void onResume();

    void onMenuCreated();

    void onPause();

    void onStop();

    void onDestroy();
}
