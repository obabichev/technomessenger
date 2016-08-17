package com.obabichev.technomessenger.presenter.activity;

import android.widget.Toolbar;

import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.presenter.activity.BaseActivityPresenter;
import com.obabichev.technomessenger.view.activity.MainView;

/**
 * Created by olegchuikin on 11/08/16.
 */

public class MainPresenter extends BaseActivityPresenter<MainView> {

    private OnBackPressedListener onBackPressedListener;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onResume() {
        super.onResume();

        if (view.isActivityStart()) {
            view.switchToSplashScreen();
        }
    }
}
