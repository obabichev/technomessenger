package com.obabichev.technomessenger.view.fragment;

import com.obabichev.technomessenger.cleanmvp.view.fragment.FragmentView;
import com.obabichev.technomessenger.view.activity.MainView;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by olegchuikin on 16/08/16.
 */

public interface ChatsListView extends FragmentView<MainView> {

    PublishSubject<Void> getAddMenuItemClicks();

    PublishSubject<Void> getCompleteMenuItemClicks();

    void switchToCreateNewChannelState();

    void switchToChannelsListState();

    boolean isCreateNewChannelState();


    String getChannelname();

    String getChannelDescription();

}
