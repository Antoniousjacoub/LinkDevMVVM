package com.example.linkdevmvvm.dagger.component;


import com.example.linkdevmvvm.dagger.module.NetworkingModule;
import com.example.linkdevmvvm.ui.base.BaseViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by antonio on 1/16/19.
 */

@Singleton
@Component(modules = {NetworkingModule.class})
public interface NetworkingComponent {
    void inject(BaseViewModel baseViewModel);
}