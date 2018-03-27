package com.tbaehr.drawernavigation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.tbaehr.drawernavigation.base.BaseDrawerActivity;
import com.tbaehr.drawernavigation.base.BaseDrawerFragment;

import java.util.HashMap;
import java.util.Map;

public class MainDrawerActivity extends BaseDrawerActivity {

    private NavigationView navigationView;

    private static class FragmentTag {
        static final String MAIN = "MAIN";
        static final String OTHER = "OTHER";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAsFullScreenActivity();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_main:
                showFragment(FragmentTag.MAIN);
                break;
            case R.id.nav_other:
                showFragment(FragmentTag.OTHER);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        return FragmentTag.MAIN;
    }

    @Override
    protected Map<String, BaseDrawerFragment> provideFragments() {
        Map<String, BaseDrawerFragment> fragments = new HashMap<>();

        fragments.put(FragmentTag.MAIN, new MainFragment());
        fragments.put(FragmentTag.OTHER, new OtherFragment());

        return fragments;
    }

    @Override
    protected NavigationView getNavigationView() {
        return navigationView;
    }
}
