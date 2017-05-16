package com.lev.mvpcleanarch.di;

import android.content.Context;

import com.lev.mvpcleanarch.App;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Module
public class ApplicationModule {

    private final App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    public App provideApplication() {
        return mApp;
    }
}
