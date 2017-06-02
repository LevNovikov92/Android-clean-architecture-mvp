package com.lev.mvpcleanarch.di;

import android.app.Activity;

import com.lev.mvpcleanarch.di.annotation.ActivityScope;

import dagger.Component;

/**
 * Author: Lev
 * Date: 02.06.2017
 */
@SuppressWarnings("WeakerAccess")
@ActivityScope
@Component(dependencies = { AppComponent.class }, modules = { ActivityModule.class })
public interface ActivityComponent {

    Activity activity();
}
