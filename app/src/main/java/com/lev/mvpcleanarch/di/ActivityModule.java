package com.lev.mvpcleanarch.di;

import android.app.Activity;

import com.lev.mvpcleanarch.di.annotation.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Lev
 * Date: 02.06.2017
 */

@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
