package com.lev.mvpcleanarch.di;

import android.content.Context;

import com.lev.mvpcleanarch.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Module
public class AppModule {

    private final App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @SuppressWarnings("WeakerAccess")
    @Singleton
    @Provides
    public Context provideContext() {
        return mApp;
    }
}
