package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighboursList;
    private String fragmentSwitch;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, String fragmentSwitch) {
        mNeighboursList = items;
        this.fragmentSwitch = fragmentSwitch;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighboursList.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mLineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(v.getContext(), DetailsNeighbourActivity.class);
                detailIntent.putExtra(DetailsNeighbourActivity.EXTRA_NEIGHBOUR, neighbour);
                v.getContext().startActivity(detailIntent);
            }
        });

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentSwitch.equals(NeighbourFavoritesFragment.class.getName())) {
                    EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(neighbour));
                } else if (fragmentSwitch.equals(NeighbourFragment.class.getName())) {
                    if (neighbour.isFavorite()) {
                        EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(neighbour));
                        EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                    } else {
                        EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighboursList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        @BindView(R.id.line_item)
        public ConstraintLayout mLineItem;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
