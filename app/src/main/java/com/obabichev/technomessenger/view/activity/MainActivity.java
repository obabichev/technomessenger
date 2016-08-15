package com.obabichev.technomessenger.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.view.activity.BaseActivity;
import com.obabichev.technomessenger.cleanmvp.view.fragment.BaseFragment;
import com.obabichev.technomessenger.interactor.SocketInteractorImpl;
import com.obabichev.technomessenger.model.Message;
import com.obabichev.technomessenger.presenter.activity.MainPresenter;
import com.obabichev.technomessenger.view.fragment.LoginScreenFragment;
import com.obabichev.technomessenger.view.fragment.SplashFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Subscriber;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {


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
        LoginScreenFragment fragment = new LoginScreenFragment();
        switchToScreen(fragment);
    }

    private void switchToScreen(BaseFragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment, fragment.TAG)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
