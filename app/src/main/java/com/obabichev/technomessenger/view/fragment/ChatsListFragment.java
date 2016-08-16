package com.obabichev.technomessenger.view.fragment;

import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.view.fragment.BaseFragment;
import com.obabichev.technomessenger.presenter.fragment.ChatsListPresenter;
import com.obabichev.technomessenger.view.activity.MainView;

/**
 * Created by olegchuikin on 16/08/16.
 */

public class ChatsListFragment extends BaseFragment<ChatsListPresenter, MainView> implements ChatsListView {
    @Override
    protected ChatsListPresenter getPresenter() {
        return new ChatsListPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.chat_list_screen;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.mainFrame;
    }
}
