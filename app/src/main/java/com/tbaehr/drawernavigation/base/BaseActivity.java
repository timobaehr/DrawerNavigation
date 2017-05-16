package com.tbaehr.drawernavigation.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.propaneapps.tomorrow.base.BasePresenterActivity;
import com.propaneapps.tomorrow.common.FactoryWithType;
import com.propaneapps.tomorrow.presenter.BasePresenter;

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends BasePresenterActivity<V, P>
        implements FactoryWithType<P> {

    private P mPresenter;

    // MVP methods

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public FactoryWithType<P> getPresenterFactory() {
        return this;
    }

    @Override
    public void onPresenterProvided(P presenter) {
        super.onPresenterProvided(presenter);
        this.mPresenter = presenter;
    }

    // Useful Activity utils

    /**
     * Translucent status bar (bar at the top) and navigation bar always visible
     */
    public void setStatusBarTranslucent() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public <T extends Activity> void startActivity(Class<T> activityClazz) {
        startActivity(new Intent(this, activityClazz));
    }

    /**
     * Create an intent with a given action and for a given data url.
     *
     * @param uri The Intent data URI.
     */
    public void openUrl(Uri uri) {
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}
