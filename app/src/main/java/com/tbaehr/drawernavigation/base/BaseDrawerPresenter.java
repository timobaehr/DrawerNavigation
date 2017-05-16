package com.tbaehr.drawernavigation.base;

import android.support.design.widget.NavigationView;

import com.propaneapps.tomorrow.presenter.BasePresenter;
import com.tbaehr.drawernavigation.MainDrawerView;

public abstract class BaseDrawerPresenter extends BasePresenter<MainDrawerView>
        implements NavigationView.OnNavigationItemSelectedListener {
}
