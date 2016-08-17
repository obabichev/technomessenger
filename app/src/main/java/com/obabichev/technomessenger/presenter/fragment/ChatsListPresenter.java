package com.obabichev.technomessenger.presenter.fragment;

import android.util.Log;
import android.view.View;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.presenter.fragment.BaseFragmentPresenter;
import com.obabichev.technomessenger.presenter.activity.OnBackPressedListener;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.fragment.ChatsListView;
import com.obabichev.technomessenger.view.fragment.SplashView;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by olegchuikin on 16/08/16.
 */

public class ChatsListPresenter extends BaseFragmentPresenter<ChatsListView, MainView> {

    private final OnBackPressedListener onBackPressedListener = new OnBackPressedListener() {
        @Override
        public boolean onBackPressed() {
            if (view.isCreateNewChannelState()){
                view.switchToChannelsListState();
                return true;
            }
            return false;
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        view.getActivityView().showActionBar();
        view.getActivityView().setOnBackPressedListener(onBackPressedListener);

        startObservingViewEvents();
    }

    @Override
    public void onPause() {
        super.onPause();

        view.getActivityView().unsetOnBackPressedListener();
    }

    private void startObservingViewEvents() {
        view.getAddMenuItemClicks().subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                view.switchToCreateNewChannelState();
            }
        });

        view.getCompleteMenuItemClicks().subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                view.switchToChannelsListState();
            }
        });
    }
}
