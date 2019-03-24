package com.example.linkdevmvvm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.dataModel.DrawerItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antonio on 1/16/19.
 */


public class CustomDrawerAdapter extends RecyclerView.Adapter<CustomDrawerAdapter.ViewHolder> {

    private int lastSelectedSideMenuPosition;//make the 0 position the default selected
    private Context context;
    private List<DrawerItem> drawerItemList;
    private OnItemSideMenuClicked onItemSideMenuClicked;

    public CustomDrawerAdapter(Context context, List<DrawerItem> listItems, OnItemSideMenuClicked onItemSideMenuClicked) {
        this.context = context;
        this.drawerItemList = listItems;
        this.onItemSideMenuClicked = onItemSideMenuClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrawerItem dItem = drawerItemList.get(position);

        holder.drawerIcon.setImageDrawable(context.getResources().getDrawable(dItem.getImgResID()));
        holder.drawerItemName.setText(dItem.getItemName());
        holder.itemLayout.setOnClickListener(view -> onItemSideMenuClicked.onItemSideMenuClicked(position));

        if (position == lastSelectedSideMenuPosition) {
            holder.imageSelectedItem.setImageDrawable(context.getResources().getDrawable(R.drawable.selected));
        } else {
            holder.imageSelectedItem.setImageDrawable(null);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return drawerItemList.size();
    }

    public void setlastSelectedPosition(int lastSelectedSideMenuPosition) {
        this.lastSelectedSideMenuPosition = lastSelectedSideMenuPosition;
        notifyDataSetChanged();
    }

    public interface OnItemSideMenuClicked {
        void onItemSideMenuClicked(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_selected_item)
        ImageView imageSelectedItem;
        @BindView(R.id.drawer_icon)
        ImageView drawerIcon;
        @BindView(R.id.drawer_itemName)
        TextView drawerItemName;
        @BindView(R.id.itemLayout)
        LinearLayout itemLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
