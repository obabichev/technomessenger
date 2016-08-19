package com.obabichev.technomessenger.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.view.fragment.BaseFragment;
import com.obabichev.technomessenger.model.Channel;
import com.obabichev.technomessenger.presenter.fragment.ChannelsListPresenter;
import com.obabichev.technomessenger.view.activity.MainView;
import com.obabichev.technomessenger.view.adapter.ChannelsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by olegchuikin on 16/08/16.
 */

public class ChannelsListFragment extends BaseFragment<ChannelsListPresenter, MainView> implements ChannelsListView {

    @BindView(R.id.new_channel_section)
    View newChannelSection;

    @BindView(R.id.channels_list)
    RecyclerView channelListRecyclerView;

    @BindView(R.id.channel_name_input)
    EditText channelNameInput;

    @BindView(R.id.channel_description_input)
    EditText channelDescriptionInput;

    private MenuItem addMenuItem;

    private MenuItem completeMenuItem;

    private PublishSubject<Void> addMenuItemPublisher = PublishSubject.create();

    private PublishSubject<Void> completeMenuItemPublisher = PublishSubject.create();

    private boolean isCreateNewChannelState;

    private List<Channel> channels;

    @Override
    protected ChannelsListPresenter getPresenter() {
        return new ChannelsListPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.chat_list_screen;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.mainFrame;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu_item:
                addMenuItemPublisher.onNext(null);
                break;
            case R.id.complete_menu_item:
                completeMenuItemPublisher.onNext(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        channelListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.channel_list_menu, menu);

        addMenuItem = menu.findItem(R.id.add_menu_item);
        completeMenuItem = menu.findItem(R.id.complete_menu_item);

        switchToChannelsListState();

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public PublishSubject<Void> getAddMenuItemClicks() {
        return addMenuItemPublisher;
    }

    @Override
    public PublishSubject<Void> getCompleteMenuItemClicks() {
        return completeMenuItemPublisher;
    }

    @Override
    public void switchToCreateNewChannelState() {
        isCreateNewChannelState = true;
        changeVisibilities(true);
    }

    @Override
    public void switchToChannelsListState() {
        isCreateNewChannelState = false;
        changeVisibilities(false);
    }

    private void changeVisibilities(boolean isCreateNewChannel) {

        addMenuItem.setVisible(!isCreateNewChannel);
        completeMenuItem.setVisible(isCreateNewChannel);
        newChannelSection.setVisibility(isCreateNewChannel ? VISIBLE : GONE);
    }

    @Override
    public boolean isCreateNewChannelState() {
        return isCreateNewChannelState;
    }

    @Override
    public void showChannelsList(List<Channel> channels) {
        this.channels = channels;
        ChannelsAdapter channelsAdapter = new ChannelsAdapter(channels);
        channelListRecyclerView.setAdapter(channelsAdapter);
    }

    @Override
    public void addChannelToList(Channel channel) {
        Log.d(App.FILTER_TAG, "Channel added: " + channel.getName());
        channels.add(channel);
        channelListRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public String getChannelName() {
        return channelNameInput.getText().toString();
    }

    @Override
    public String getChannelDescription() {
        return channelDescriptionInput.getText().toString();
    }

    @Override
    public void clearInputs() {
        channelNameInput.setText("");
        channelDescriptionInput.setText("");

    }


}
