package com.example.linkdevmvvm.ui.homeNewsFeed;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.adapters.NewsFeedAdapter;
import com.example.linkdevmvvm.dataModel.Article;
import com.example.linkdevmvvm.databinding.FragmentHomeNewsFeedBinding;
import com.example.linkdevmvvm.ui.base.BaseFragment;
import com.example.linkdevmvvm.utils.Utils;

import butterknife.BindView;


public class HomeNewsFeedFragment extends BaseFragment<FragmentHomeNewsFeedBinding, HomeNewsFeedViewModel> implements NewsFeedAdapter.OnItemNewsClicked {

    public static final String TAG = "HomeFragment";

    private Context context;
    private HomeNewsFeedViewModel homeNewsFeedViewModel;

    @BindView(R.id.rv_news_feed)
    RecyclerView recyclerView;
    FragmentHomeNewsFeedBinding fragmentHomeNewsFeedBinding;

    public static HomeNewsFeedFragment getInstance() {
        return new HomeNewsFeedFragment();
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_news_feed;
    }

    @Override
    protected HomeNewsFeedViewModel getViewModel() {
        homeNewsFeedViewModel = ViewModelProviders.of(this).get(HomeNewsFeedViewModel.class);
        return homeNewsFeedViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        fragmentHomeNewsFeedBinding = getViewDataBinding();
        handleNewsRequest();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void handleNewsRequest() {
        if (context == null)
            return;
        homeNewsFeedViewModel = ViewModelProviders.of(this).get(HomeNewsFeedViewModel.class);
        homeNewsFeedViewModel.getNewsFeed();
        homeNewsFeedViewModel.getHomeNewsResponse().observe(this, newsFeedResponse -> {
            // Update the UI
            if (newsFeedResponse == null || context == null) {
                Utils.showMessage(context, getString(R.string.no_data_to_show));
                return;
            }
            fragmentHomeNewsFeedBinding.rvNewsFeed.setLayoutManager(new LinearLayoutManager(context));
            NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(context, newsFeedResponse.getArticles());
            fragmentHomeNewsFeedBinding.rvNewsFeed.setAdapter(newsFeedAdapter);
        });
    }


    @Override
    public void onItemNewsClicked(Article article, int position) {
//        Intent intent = new Intent(context, NewsFeedDetailsActivity.class);
//        intent.putExtra(NewsDetailsFragment.ARTICLE_KEY, article);
//        startActivity(intent);
    }


}

