package com.tbaehr.drawernavigation.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;

import com.propaneapps.tomorrow.presenter.BasePresenter;

import java.util.Map;

public abstract class BaseDrawerActivity<V, P extends BasePresenter<V>> extends BaseActivity<V, P> {

    private Map<String, BaseDrawerFragment> mFragmentMap;

    private Bundle mSavedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
    }

    @Override
    public void onPresenterProvided(P presenter) {
        super.onPresenterProvided(presenter);

        mFragmentMap = provideFragments();

        // If we're being restored from a previous state,
        // then we don't need to do anything and should return
        // or else we could end up with overlapping fragments.
        if (mSavedInstanceState != null) {
            return;
        }

        showFragment(getDefaultFragmentTag());
    }

    protected void showFragment(final String tagOfFragment) {
        BaseDrawerFragment drawerFragment = mFragmentMap.get(tagOfFragment);

        getSupportFragmentManager().beginTransaction()
                .replace(getFragmentContainerViewId(), drawerFragment, getDefaultFragmentTag())
                .addToBackStack(null)
                .commitAllowingStateLoss();
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
