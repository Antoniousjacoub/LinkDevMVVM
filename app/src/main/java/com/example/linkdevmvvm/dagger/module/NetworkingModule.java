package com.example.linkdevmvvm.dagger.module;

import com.example.linkdevmvvm.service.ServicesInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by antonio on 1/16/19.
 */

@Module
public class NetworkingModule {

    private String mBaseUrl;

    public NetworkingModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    private Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    ServicesInterface getServicesInterface() {
        return provideRetrofit().create(ServicesInterface.class);
    }

    private OkHttpClient provideOkhttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(logging)
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();
        return okHttpClient;

    }


    private Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .baseUrl(mBaseUrl)
                .client(provideOkhttpClient())
                .build();
    }
}
