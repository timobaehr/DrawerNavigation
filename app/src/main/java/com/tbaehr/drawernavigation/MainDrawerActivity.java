package com.tbaehr.drawernavigation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.stroeer.t_online.tomonext.R;
import com.tbaehr.drawernavigation.base.BaseDrawerActivity;
import com.tbaehr.drawernavigation.base.BaseDrawerFragment;

import java.util.Map;

public class MainDrawerActivity extends BaseDrawerActivity<MainDrawerView, MainDrawerPresenter> implements MainDrawerView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarTranslucent();
    }

    @Override
    public void onPresenterProvided(MainDrawerPresenter presenter) {
        super.onPresenterProvided(presenter);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(presenter);
    }

    @Override
    public Class<? extends MainDrawerPresenter> getTypeClazz() {
        return MainDrawerPresenter.class;
    }

    @Override
    public MainDrawerPresenter create() {
        return new MainDrawerPresenter();
    }

    @Override
    public MainDrawerView getViewLayer() {
        return this;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return getPresenter().onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    public int getFragmentContainerViewId() {
        return R.id.fragment_container;
    }

    @Override
    protected String getDefaultFragmentTag() {
        return getPresenter().getDefaultFragmentTag();
    }

    @Override
    protected Map<String, BaseDrawerFragment> provideFragments() {
        return getPresenter().provideFragments();
    }

    @Override
    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showFragment(String fragmentTag) {
        super.showFragment(fragmentTag);
    }
}
