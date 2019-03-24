package com.example.linkdevmvvm.ui.newsFeedDetails;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.linkdevmvvm.BR;
import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.dataModel.Article;
import com.example.linkdevmvvm.databinding.FragmentNewsDetailsBinding;
import com.example.linkdevmvvm.ui.base.BaseFragment;
import com.example.linkdevmvvm.utils.Utils;

public class NewsDetailsFragment extends BaseFragment<FragmentNewsDetailsBinding, NewsDetailsViewModel> {

    public static final String TAG = "NewsDetailsFragmentTag";
    public static String ARTICLE_KEY = "ARTICLE_KEY";
    private Context context;
    private NewsDetailsViewModel newsDetailsViewModel;
    private FragmentNewsDetailsBinding fragmentNewsDetailsBinding;

    public static NewsDetailsFragment getInstance(Bundle bundle) {
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        fragmentNewsDetailsBinding = getViewDataBinding();
        newsDetailsViewModel.handleNewsFeedDetailsData(getArguments());
        newsDetailsViewModel.getArticleMutableLiveData().observe(this, this::onSetDataOnView);
    }

    @Override
    protected int getBindingVariable() {
        return BR.newsDetailsModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_details;
    }

    @Override
    protected NewsDetailsViewModel getViewModel() {
        newsDetailsViewModel = ViewModelProviders.of(this).get(NewsDetailsViewModel.class);
        return newsDetailsViewModel;
    }

    private void onSetDataOnView(Article article) {
        if (article == null)
            return;
        fragmentNewsDetailsBinding.tvAuthor.setText(Utils.validString(article.getAuthor()));
        fragmentNewsDetailsBinding.tvNewsFeedTitle.setText(Utils.validString(article.getTitle()));
        fragmentNewsDetailsBinding.tvNewsDetailsDesc.setText(Utils.validString(article.getDescription()));
        fragmentNewsDetailsBinding.tvDatePublished.setText(Utils.parseDate(article.getPublishedAt()));
        Utils.loadImageWithGlide(context, fragmentNewsDetailsBinding.imgNewsFeedDetails, article.getUrlToImage());


    }


}
