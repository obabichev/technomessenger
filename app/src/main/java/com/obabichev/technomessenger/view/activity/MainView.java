package com.obabichev.technomessenger.view.activity;

import com.obabichev.technomessenger.cleanmvp.view.activity.ActivityView;
import com.obabichev.technomessenger.presenter.activity.OnBackPressedListener;

/**
 * Created by olegchuikin on 11/08/16.
 */

public interface MainView extends ActivityView {

    boolean isActivityStart();

    void switchToSplashScreen();

    void switchToLoginScreen();

    void hideActionBar();

    void showActionBar();

    void setOnBackPressedListener(OnBackPressedListener listener);

    void unsetOnBackPressedListener();
}
