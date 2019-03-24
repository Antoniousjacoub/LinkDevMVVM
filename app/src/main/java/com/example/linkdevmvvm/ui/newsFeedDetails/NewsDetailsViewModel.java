package com.example.linkdevmvvm.ui.newsFeedDetails;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.dataModel.Article;
import com.example.linkdevmvvm.ui.base.BaseViewModel;
import com.example.linkdevmvvm.utils.Utils;

import static com.example.linkdevmvvm.ui.newsFeedDetails.NewsDetailsFragment.ARTICLE_KEY;

public class NewsDetailsViewModel extends BaseViewModel {
    private Application application;

    private MutableLiveData<Article> articleMutableLiveData = new MutableLiveData<>();

    public NewsDetailsViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    void handleNewsFeedDetailsData(Bundle bundle) {
        if (bundle == null) {
            Utils.showMessage(application, application.getString(R.string.somthing_went_wrong));
            return;
        }
        Article article = bundle.getParcelable(ARTICLE_KEY);
        articleMutableLiveData.setValue(article);

    }

    MutableLiveData<Article> getArticleMutableLiveData() {
        return articleMutableLiveData;
    }

    public void openWebsiteOnBrowser() {
        if (articleMutableLiveData.getValue() == null) return;
        Utils.openWebsiteOnBrowser(application, articleMutableLiveData.getValue().getUrl());
    }
}
