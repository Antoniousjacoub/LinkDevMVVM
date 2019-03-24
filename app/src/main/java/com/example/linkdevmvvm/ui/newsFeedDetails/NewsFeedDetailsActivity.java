package com.example.linkdevmvvm.ui.newsFeedDetails;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;

import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedDetailsActivity extends BaseActivity {


    @BindView(R.id.container_home)
    FrameLayout containerHome;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_details);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.link_development));
        addFragment(R.id.container_home, NewsDetailsFragment.getInstance(getIntent().getExtras()), NewsDetailsFragment.TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}