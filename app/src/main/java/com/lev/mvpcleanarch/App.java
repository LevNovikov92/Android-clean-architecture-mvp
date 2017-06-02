package com.lev.mvpcleanarch;

import android.app.Application;

import com.lev.mvpcleanarch.di.AppComponent;
import com.lev.mvpcleanarch.di.AppModule;
import com.lev.mvpcleanarch.di.DaggerAppComponent;
import com.lev.mvpcleanarch.di.DataModule;
import com.lev.mvpcleanarch.di.DomainModule;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class App extends Application {

    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDI();
    }

    private void initializeDI() {
        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule(this))
                .domainModule(new DomainModule())
                .build();
    }

    public AppComponent getComponent() {
        return mComponent;
    }
}
