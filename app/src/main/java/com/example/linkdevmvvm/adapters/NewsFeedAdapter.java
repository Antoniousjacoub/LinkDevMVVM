package com.example.linkdevmvvm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.dataModel.Article;
import com.example.linkdevmvvm.databinding.ItemNewsFeedBinding;

import java.util.List;

/**
 * Created by antonio on 1/16/19.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {

    private List<Article> mData;
    private OnItemNewsClicked onItemNewsClicked;


    public NewsFeedAdapter(List<Article> data, OnItemNewsClicked onItemNewsClicked) {
        this.mData = data;
        this.onItemNewsClicked = onItemNewsClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsFeedBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_news_feed, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = mData.get(position);
        holder.bind(article, onItemNewsClicked);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemNewsClicked {
        void onItemNewsClicked(Article article);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemNewsFeedBinding itemRowBinding;

        ViewHolder(ItemNewsFeedBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        void bind(Article article, OnItemNewsClicked onItemNewsClicked) {
            itemRowBinding.setArtical(article);
            itemRowBinding.setItemClickListener(onItemNewsClicked);
            itemRowBinding.executePendingBindings();
        }

    }
}
