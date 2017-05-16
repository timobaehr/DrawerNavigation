package com.tbaehr.drawernavigation;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.propaneapps.tomorrow.presenter.BasePresenter;
import com.stroeer.t_online.tomonext.R;
import com.tbaehr.drawernavigation.base.BaseDrawerFragment;
import com.tbaehr.drawernavigation.base.BaseDrawerPresenter;

import java.util.HashMap;
import java.util.Map;

public class MainDrawerPresenter extends BaseDrawerPresenter {

    private static class FragmentTag {
        static final String MAIN = "MAIN";
        static final String OTHER = "OTHER";
    }

    public Map<String, BaseDrawerFragment> provideFragments() {
        Map<String, BaseDrawerFragment> fragments = new HashMap<>();

        fragments.put(FragmentTag.MAIN, new MainFragment());
        fragments.put(FragmentTag.OTHER, new OtherFragment());

        return fragments;
    }

    public String getDefaultFragmentTag() {
        return FragmentTag.MAIN;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_main:
                getView().showFragment(FragmentTag.MAIN);
                break;
            case R.id.nav_other:
                getView().showFragment(FragmentTag.OTHER);
                break;
        }

        getView().closeDrawer();
        return true;
    }

}
