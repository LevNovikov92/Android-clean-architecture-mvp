package com.lev.mvpcleanarch.di;

import android.content.Context;

import com.lev.mvpcleanarch.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    public Application provideApplication() {
        return mApplication;
    }
}
