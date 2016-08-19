package com.obabichev.technomessenger.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.model.Channel;

import java.util.List;

/**
 * Created by olegchuikin on 18/08/16.
 */

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelViewHolder> {

    private List<Channel> channels;

    public ChannelsAdapter(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_card, parent, false);
        return new ChannelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        holder.name.setText(String
                .format("%s(%d)", channels.get(position).getName(), channels.get(position).getOnline()));
        holder.description.setText(channels.get(position).getDescr());

    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;

        public ChannelViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.channel_list_item_name);
            description = (TextView) itemView.findViewById(R.id.channel_list_item_descr);
        }
    }

}
