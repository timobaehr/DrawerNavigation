package com.tbaehr.drawernavigation.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stroeer.t_online.tomonext.R;

public abstract class BaseDrawerFragment extends Fragment {

    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);

        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        getBaseDrawerActivity().setSupportActionBar(mToolbar);

        DrawerLayout drawer = getBaseDrawerActivity().getDrawerLayout();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getBaseDrawerActivity(), drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setToolbarTitle(getTitleTextRes());

        return rootView;
    }

    private void setToolbarTitle(@StringRes int title) {
        ActionBar actionBar = getBaseDrawerActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        } else {
            getBaseDrawerActivity().setTitle(title);
            mToolbar.setTitle(title);
        }
    }

    protected abstract @StringRes int getTitleTextRes();

    protected abstract @LayoutRes int getLayoutId();

    public final BaseDrawerActivity getBaseDrawerActivity() {
        return (BaseDrawerActivity) super.getActivity();
    }
}
