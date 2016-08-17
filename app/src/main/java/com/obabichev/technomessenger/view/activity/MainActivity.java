package com.obabichev.technomessenger.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.view.activity.BaseActivity;
import com.obabichev.technomessenger.cleanmvp.view.fragment.BaseFragment;
import com.obabichev.technomessenger.presenter.activity.MainPresenter;
import com.obabichev.technomessenger.presenter.activity.OnBackPressedListener;
import com.obabichev.technomessenger.view.fragment.LoginFragment;
import com.obabichev.technomessenger.view.fragment.SplashFragment;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private OnBackPressedListener onBackPressedListener;

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public boolean isActivityStart() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        return fragments == null || fragments.isEmpty();
    }

    @Override
    public void switchToSplashScreen() {
        SplashFragment fragment = new SplashFragment();
        switchToScreen(fragment);
    }

    @Override
    public void switchToLoginScreen() {
        Log.d(App.SOCKET_TAG, "Switch to login screen");
        LoginFragment fragment = new LoginFragment();
        switchToScreen(fragment);
    }

    @Override
    public void hideActionBar() {
        getSupportActionBar().hide();
    }

    @Override
    public void showActionBar() {
        getSupportActionBar().show();
    }

    @Override
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void unsetOnBackPressedListener() {
        onBackPressedListener = null;
    }

    private void switchToScreen(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment, fragment.TAG)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public void onBackPressed() {

        if (onBackPressedListener != null && onBackPressedListener.onBackPressed()){
            return;
        }

        super.onBackPressed();
    }
}
