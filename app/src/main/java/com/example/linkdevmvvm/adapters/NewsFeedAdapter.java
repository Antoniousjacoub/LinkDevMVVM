package com.example.linkdevmvvm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.Article;
import com.example.antonio.linkdevtask.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antonio on 1/16/19.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {

    private List<Article> mData;
    private Context context;
    private OnItemNewsClicked onItemNewsClicked;

    // data is passed into the constructor
    public NewsFeedAdapter(Context context, List<Article> data, OnItemNewsClicked onItemNewsClicked) {
        this.mData = data;
        this.context = context;
        this.onItemNewsClicked = onItemNewsClicked;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_feed, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = mData.get(position);
        holder.tvAuthor.setText(Utils.validString(article.getAuthor()));
        holder.tvNewsFeedTitle.setText(Utils.validString(article.getTitle()));
        holder.tvPublishDate.setText(Utils.parseDate(article.getPublishedAt()));
        Utils.loadImageWithGlide(context, holder.imgNewsFeed, article.getUrlToImage());

        holder.cardView.setOnClickListener(v -> {
            if (onItemNewsClicked != null)
                onItemNewsClicked.onItemNewsClicked(mData.get(position), position);
        });

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemNewsClicked {
        void onItemNewsClicked(Article article, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_news_feed)
        ImageView imgNewsFeed;
        @BindView(R.id.tv_news_feed_title)
        TextView tvNewsFeedTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_publish_date)
        TextView tvPublishDate;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
