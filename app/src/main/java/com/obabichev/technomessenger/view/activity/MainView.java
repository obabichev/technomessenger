package com.obabichev.technomessenger.view.activity;

import com.obabichev.technomessenger.cleanmvp.view.activity.ActivityView;

/**
 * Created by olegchuikin on 11/08/16.
 */

public interface MainView extends ActivityView {

    boolean isActivityStart();

    void switchToSplashScreen();

    void switchToLoginScreen();
}
