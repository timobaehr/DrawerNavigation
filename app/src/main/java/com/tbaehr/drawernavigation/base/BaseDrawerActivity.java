package com.tbaehr.drawernavigation.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Map;

public abstract class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Map<String, BaseDrawerFragment> mFragmentMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentMap = provideFragments();

        // If we're being restored from a previous state,
        // then we don't need to do anything and should return
        // or else we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }

        showFragment(getDefaultFragmentTag());
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

    protected void showFragment(String tagOfFragment) {
        BaseDrawerFragment drawerFragment = mFragmentMap.get(tagOfFragment);

        getSupportFragmentManager().beginTransaction()
                .replace(getFragmentContainerViewId(), drawerFragment, getDefaultFragmentTag())
                .addToBackStack(null)
                .commit();
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

}
