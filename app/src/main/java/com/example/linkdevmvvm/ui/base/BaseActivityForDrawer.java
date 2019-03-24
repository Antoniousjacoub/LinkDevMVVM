package com.example.linkdevmvvm.ui.base;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;

import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.adapters.CustomDrawerAdapter;
import com.example.linkdevmvvm.dataModel.DrawerItem;
import com.example.linkdevmvvm.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivityForDrawer extends BaseActivity implements CustomDrawerAdapter.OnItemSideMenuClicked {

    @BindView(R.id.flContent)
    public FrameLayout flContent;
    @BindView(R.id.load_view)
    public FrameLayout loadView;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.rv_menuList)
    RecyclerView rvMenuList;
    @BindView(R.id.nvView)
    NavigationView nvView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private CustomDrawerAdapter customDrawerAdapter;

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base_for_drawer, null);
        FrameLayout activityContainer = fullView.findViewById(R.id.flContent);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    protected void setupDrawer() {

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        ArrayList<DrawerItem> dataListOFMenuItems = new ArrayList<>();
        DrawerItem item_1 = new DrawerItem();
        item_1.setItemName(getString(R.string.str_menu_explore));
        item_1.setImgResID(R.drawable.ic_explore);
        dataListOFMenuItems.add(item_1);

        DrawerItem item_2 = new DrawerItem();
        item_2.setItemName(getString(R.string.str_menu_live_chat));
        item_2.setImgResID(R.drawable.ic_live);
        dataListOFMenuItems.add(item_2);
        DrawerItem item_3 = new DrawerItem();
        item_3.setItemName(getString(R.string.str_menu_gallery));
        item_3.setImgResID(R.drawable.ic_gallery);
        dataListOFMenuItems.add(item_3);
        DrawerItem item_4 = new DrawerItem();
        item_4.setItemName(getString(R.string.str_menu_wish_list));
        item_4.setImgResID(R.drawable.ic_wishlist);
        dataListOFMenuItems.add(item_4);
        DrawerItem item_5 = new DrawerItem();
        item_5.setItemName(getString(R.string.str_menu_e_magazine));

        item_5.setImgResID(R.drawable.ic_e_magazine);
        dataListOFMenuItems.add(item_5);
        customDrawerAdapter = new CustomDrawerAdapter(this, dataListOFMenuItems, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMenuList.setLayoutManager(layoutManager);
        rvMenuList.setAdapter(customDrawerAdapter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @Override
    public void onItemSideMenuClicked(int position) {
        drawerLayout.closeDrawers();
        customDrawerAdapter.setlastSelectedPosition(position);

        switch (position) {
            case SideMenuItems.EXPLORE:
                Utils.showMessage(this, getString(R.string.str_menu_explore));
                break;
            case SideMenuItems.LIVE_CHAT:
                Utils.showMessage(this, getString(R.string.str_menu_live_chat));
                break;
            case SideMenuItems.GALLERY:
                Utils.showMessage(this, getString(R.string.str_menu_gallery));
                break;
            case SideMenuItems.WISH_LIST:
                Utils.showMessage(this, getString(R.string.str_menu_wish_list));
                break;
            case SideMenuItems.E_MAGAZINE:
                Utils.showMessage(this, getString(R.string.str_menu_e_magazine));
                break;


        }
    }

    private static class SideMenuItems {
        private final static int EXPLORE = 0;
        private final static int LIVE_CHAT = 1;
        private final static int GALLERY = 2;
        private final static int WISH_LIST = 3;
        private final static int E_MAGAZINE = 4;
    }
}
