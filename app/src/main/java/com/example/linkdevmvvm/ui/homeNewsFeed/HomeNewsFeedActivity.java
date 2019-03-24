package com.example.linkdevmvvm.ui.homeNewsFeed;

import android.os.Bundle;

import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.ui.base.BaseActivityForDrawer;

import butterknife.ButterKnife;

public class HomeNewsFeedActivity extends BaseActivityForDrawer {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.link_development));
        setupDrawer();
        if (savedInstanceState == null)
            addFragment(R.id.container_home, HomeNewsFeedFragment.getInstance(), HomeNewsFeedFragment.TAG);
    }


}
