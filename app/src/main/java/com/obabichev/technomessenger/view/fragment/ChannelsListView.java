package com.obabichev.technomessenger.view.fragment;

import com.obabichev.technomessenger.cleanmvp.view.fragment.FragmentView;
import com.obabichev.technomessenger.model.Channel;
import com.obabichev.technomessenger.view.activity.MainView;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by olegchuikin on 16/08/16.
 */

public interface ChannelsListView extends FragmentView<MainView> {

    PublishSubject<Void> getAddMenuItemClicks();

    PublishSubject<Void> getCompleteMenuItemClicks();

    void switchToCreateNewChannelState();

    void switchToChannelsListState();

    boolean isCreateNewChannelState();

    void showChannelsList(List<Channel> channels);


    String getChannelname();

    String getChannelDescription();

}
