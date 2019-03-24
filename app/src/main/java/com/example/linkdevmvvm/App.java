package com.example.linkdevmvvm;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.example.linkdevmvvm.dagger.component.DaggerNetworkingComponent;
import com.example.linkdevmvvm.dagger.component.NetworkingComponent;
import com.example.linkdevmvvm.dagger.module.NetworkingModule;
import com.example.linkdevmvvm.utils.Constants;

public class App extends Application {
    private static NetworkingComponent mNetworkingComponent;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static NetworkingComponent getNetComponent() {
        return mNetworkingComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNetworkingComponent = DaggerNetworkingComponent.builder()
                .networkingModule(new NetworkingModule(Constants.BASE_URL))
                .build();

    }


}