package com.tbaehr.drawernavigation.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Map;

public abstract class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String KEY_ACTIVE_FRAGMENT = "active fragment";

    private String activeFragmentTag;

    private Map<String, BaseDrawerFragment> mFragmentMap;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mFragmentMap = provideFragments();

        if (savedInstanceState == null) {
            activeFragmentTag = getDefaultFragmentTag();
            showFragment(activeFragmentTag);
        } else {
            activeFragmentTag = savedInstanceState.getString(KEY_ACTIVE_FRAGMENT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (activeFragmentTag != null) {
            outState.putString(KEY_ACTIVE_FRAGMENT, activeFragmentTag);
        }
        super.onSaveInstanceState(outState);
    }

    public void setAsFullScreenActivity() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * The attached fragments need to bind the ActionBarDrawerToggle
     * with the DrawerLayout, return something like
     * (DrawerLayout) findViewById(R.id.drawer_layout)
     *
     * @return DrawerLayout where to inject the ActionBarDrawerToggle
     */
    public abstract DrawerLayout getDrawerLayout();

    /**
     * Tell this base activity where the Fragment content is living,
     * e.g. return something like R.id.fragment_container
     *
     * @return ID of the fragment container view
     */
    public abstract @IdRes int getFragmentContainerViewId();

    public void showFragment(String tagOfFragment) {
        BaseDrawerFragment drawerFragment = mFragmentMap.get(tagOfFragment);
        activeFragmentTag = tagOfFragment;

        if (tagOfFragment.equals(getDefaultFragmentTag())) {
            getNavigationView().getMenu().getItem(0).setChecked(true);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(getFragmentContainerViewId(), drawerFragment, getDefaultFragmentTag())
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = getDrawerLayout();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!activeFragmentTag.equals(getDefaultFragmentTag())) {
            showFragment(getDefaultFragmentTag());
        } else {
            super.onBackPressed();
        }
    }

    /**
     * This will be called always when calling showFragment(String tagOfFragment).
     *
     * @return TAG of that Fragment that should be shown by default
     */
    protected abstract String getDefaultFragmentTag();

    /**
     * This will be called only once during onCreate() method.
     *
     * @return Map<String, BaseDrawerFragment> where
     * each BaseDrawerFragment is stored together with
     * its unique Fragment TAG
     */
    protected abstract Map<String, BaseDrawerFragment> provideFragments();

    protected abstract NavigationView getNavigationView();

}
