package com.example.linkdevmvvm.ui.homeNewsFeed;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;


import com.example.linkdevmvvm.App;
import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.dataModel.Article;
import com.example.linkdevmvvm.dataModel.NewsFeedResponse;
import com.example.linkdevmvvm.service.ServicesInterface;
import com.example.linkdevmvvm.ui.base.BaseViewModel;
import com.example.linkdevmvvm.utils.Constants;
import com.example.linkdevmvvm.utils.Utils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
;

/**
 * Created by antonio on 1/16/19.
 */
public class HomeNewsFeedViewModel extends BaseViewModel {

    private MutableLiveData<NewsFeedResponse> articleList=new MutableLiveData<>();
    private Application application;

    public HomeNewsFeedViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }


    void getNewsFeed() {
        Call<NewsFeedResponse> homeData = getServicesInterface().getNewsFeed(Constants.SOURCE, Constants.API_KEY);
        setCurrentRetrofitCall(homeData);
        homeData.enqueue(new Callback<NewsFeedResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsFeedResponse> call, @NonNull final Response<NewsFeedResponse> response) {
                if (response.isSuccessful()&&response.body()!=null) {
                    articleList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsFeedResponse> call, @NonNull Throwable t) {
                processError(t);
            }
        });
    }

     MutableLiveData<NewsFeedResponse> getHomeNewsResponse(){
        return articleList;
    }
    private void processError(Throwable throwable) {

        if (throwable instanceof HttpException) {
            Utils.showMessage(application.getApplicationContext(),((HttpException) throwable).message());
        } else if (throwable instanceof IOException) {
            Utils.showMessage(application.getApplicationContext(),application.getApplicationContext().getString(R.string.error_network));
        } else {
            Utils.showMessage(application.getApplicationContext(),application.getApplicationContext().getString(R.string.error_communicating_with_server));
        }
    }
}

