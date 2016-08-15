package com.obabichev.technomessenger.view.fragment;

import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.view.fragment.BaseFragment;
import com.obabichev.technomessenger.presenter.fragment.SplashPresenter;
import com.obabichev.technomessenger.view.activity.MainView;

/**
 * Created by olegchuikin on 11/08/16.
 */

public class SplashFragment extends BaseFragment<SplashPresenter, MainView> {

    @Override
    protected SplashPresenter getPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.splash_screen;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.mainFrame;
    }
}
