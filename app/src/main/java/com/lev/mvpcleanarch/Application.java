package com.lev.mvpcleanarch;

import com.lev.mvpcleanarch.di.AppComponent;
import com.lev.mvpcleanarch.di.ApplicationModule;
import com.lev.mvpcleanarch.di.DaggerAppComponent;
import com.lev.mvpcleanarch.di.DataModule;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class Application extends android.app.Application {

    private static AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDI();
    }

    private void initializeDI() {
        mComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public static AppComponent getComponent() {
        return mComponent;
    }
}
